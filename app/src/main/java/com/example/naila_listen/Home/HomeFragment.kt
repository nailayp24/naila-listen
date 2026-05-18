package com.example.naila_listen.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.Home.pertemuan_10.HomeTabsAdapter
import com.example.naila_listen.Home.pertemuan_2.SecondActivity
import com.example.naila_listen.Home.pertemuan_3.ThirdActivity
import com.example.naila_listen.Home.pertemuan_4.CustomSatuActivity // sesuaikan jika namanya FourthActivity
import com.example.naila_listen.Home.pertemuan_6.WebViewActivity
import com.example.naila_listen.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        // --- INISIALISASI TAB LAYOUT & RECIPROCAL VIEW PAGER ---
        val tabsAdapter = HomeTabsAdapter(this)
        binding.viewPagerHome.adapter = tabsAdapter

        TabLayoutMediator(binding.tabLayoutHome, binding.viewPagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Logistik"
                1 -> tab.text = "Medis"
                2 -> tab.text = "Posko Desa"
            }
        }.attach()

        // --- PROSES LOGOUT ---
        binding.btnMenuLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin keluar dari SIGANA?")
                .setPositiveButton("Ya") { dialog, _ ->
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}