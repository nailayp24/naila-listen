package com.example.naila_listen.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

// --- IMPORT API & MODEL (DATA LAYER) ---
import com.example.naila_listen.data.api.BinaDesaApiClient
import com.example.naila_listen.data.model.EarthquakeResponse
import com.example.naila_listen.data.model.EarthquakeListResponse

// --- IMPORT ADAPTER KONTEN ---
import com.example.naila_listen.Home.NewsAdapter
import com.example.naila_listen.Home.pertemuan_10.HomeTabsAdapter

// --- IMPORT INTEGRASI NAVIGASI PERTEMUAN ---
import com.example.naila_listen.Home.pertemuan_2.SecondActivity
import com.example.naila_listen.Home.pertemuan_3.ThirdActivity
import com.example.naila_listen.Home.pertemuan_4.CustomSatuActivity
import com.example.naila_listen.Home.pertemuan_6.WebViewActivity
import com.example.naila_listen.Home.EighthActivity
import com.example.naila_listen.Home.feature_camera_qr.ThirteenthActivity // Alamat package kustom baru!

// --- IMPORT UI COMPONENTS & MATERIAL DESIGN ---
import com.example.naila_listen.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        // --- SETUP TOOLBAR ---
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "SIGANA Portal"
        }

        // --- NAVIGASI INTEGRASI TOMBOL PERTEMUAN ---
        binding.btnPertemuan2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        binding.btnPertemuan3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        /* Atribut Tambahan Praktikum P4:
          Mengirim data statis institusi PCR ke layar tujuan CustomSatuActivity
        */
        binding.btnPertemuan4.setOnClickListener {
            val intent = Intent(requireContext(), CustomSatuActivity::class.java)
            intent.putExtra("nama", "Politeknik Caltex Riau")
            intent.putExtra("umur", 25)
            startActivity(intent)
        }

        binding.btnPertemuan6.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        binding.btnPertemuan8.setOnClickListener {
            startActivity(Intent(requireContext(), EighthActivity::class.java))
        }

        // 🌟 SAMBUNGAN KLIK BARU KE MANAJEMEN FOTO & SCAN QR 🌟
        binding.btnPertemuan13.setOnClickListener {
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)
            startActivity(intent)
        }

        // === SETUP RECYCLERVIEW DAFTAR BERITA BENCANA ===
        newsAdapter = NewsAdapter(emptyList())
        binding.rvBeritaBencana.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBeritaBencana.adapter = newsAdapter

        // Jalankan sinkronisasi data internet awal
        loadBinaDesaNews()

        binding.btnRefreshNews.setOnClickListener {
            loadBinaDesaNews()
        }

        // --- INTEGRASI TAB LAYOUT & VIEW PAGER 2 (P10) ---
        val tabsAdapter = HomeTabsAdapter(this)
        binding.viewPagerHome.adapter = tabsAdapter

        TabLayoutMediator(binding.tabLayoutHome, binding.viewPagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Logistik"
                1 -> tab.text = "Medis"
                2 -> tab.text = "Posko Desa"
            }
        }.attach()

        // --- PROSES KELUAR SISTEM (LOGOUT) ---
        binding.btnMenuLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout")
                .setMessage("Yakin ingin keluar dari SIGANA?")
                .setPositiveButton("Iya") { dialog, _ ->
                    dialog.dismiss()
                    sharedPref.edit().clear().apply()
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Batal logout")
                }
                .show()
        }
    }

    // --- MANAJEMEN CALL API TERPISAH ---
    private fun loadBinaDesaNews() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.tvGempaWilayah.text = "Mengunduh data bencana BMKG..."
            binding.tvGempaDetail.text = ""
            binding.tvGempaPotensi.text = ""

            // JALUR 1: Load Card Info Utama Gempa Live
            try {
                val responseMain = BinaDesaApiClient.apiService.getLiveEarthquake()
                val gempaTerkini = responseMain.infoGempa.detailGempa

                if (gempaTerkini != null) {
                    binding.tvGempaWilayah.text = "Lokasi: ${gempaTerkini.wilayah}"
                    binding.tvGempaDetail.text = "Kekuatan: ${gempaTerkini.magnitude} SR | Waktu: ${gempaTerkini.tanggal} (${gempaTerkini.jam})"
                    binding.tvGempaPotensi.text = "Status: ${gempaTerkini.potensi} (Kedalaman: ${gempaTerkini.kedalaman})"
                } else {
                    binding.tvGempaWilayah.text = "Data gempa utama tidak ditemukan."
                }
            } catch (e: Exception) {
                Log.e("SIGANA_API_ERROR", "Gagal load Card Utama: ${e.message}")
                binding.tvGempaWilayah.text = "Gagal memuat info Gempa Utama BMKG."
            }

            // JALUR 2: Load Daftar Berita Gempa Berkala (RecyclerView)
            try {
                val responseList = BinaDesaApiClient.apiService.getEarthquakeList()
                val listData = responseList.infoGempaList.listGempa

                if (listData != null) {
                    newsAdapter.updateData(listData)
                }
            } catch (e: Exception) {
                Log.e("SIGANA_API_ERROR", "Gagal load RecyclerView List: ${e.message}")
                newsAdapter.updateData(emptyList())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}