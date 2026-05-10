package com.example.naila_listen.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.naila_listen.Home.pertemuan_2.SecondActivity
import com.example.naila_listen.Home.pertemuan_3.ThirdActivity
import com.example.naila_listen.Home.pertemuan_4.CustomSatuActivity
import com.example.naila_listen.Home.pertemuan_6.WebViewActivity
import com.example.naila_listen.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set judul toolbar manual (Sesuai ID toolbar di XML)
        binding.toolbar.title = "Dashboard SIGANA"

        // Navigasi Menu Petak
        binding.btnPertemuan2.setOnClickListener { startActivity(Intent(requireContext(), SecondActivity::class.java)) }
        binding.btnPertemuan3.setOnClickListener { startActivity(Intent(requireContext(), ThirdActivity::class.java)) }
        binding.btnPertemuan4.setOnClickListener { startActivity(Intent(requireContext(), CustomSatuActivity::class.java)) }
        binding.btnPertemuan6.setOnClickListener { startActivity(Intent(requireContext(), WebViewActivity::class.java)) }

        // Navigasi ke P8 (Pastikan EighthActivity.kt sudah ada)
        binding.btnPertemuan8.setOnClickListener {
            startActivity(Intent(requireContext(), EighthActivity::class.java))
        }

        binding.btnMenuLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout").setMessage("Yakin ingin keluar dari SIGANA?")
                .setPositiveButton("Iya") { _, _ -> requireActivity().finish() }
                .setNegativeButton("Batal", null).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}