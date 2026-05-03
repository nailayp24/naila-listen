package com.example.naila_listen.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Dashboard SIGANA"

        // Pertemuan 2
        binding.btnPertemuan2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        // Pertemuan 3
        binding.btnPertemuan3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        // Pertemuan 4
        binding.btnPertemuan4.setOnClickListener {
            startActivity(Intent(requireContext(), CustomSatuActivity::class.java))
        }

        // Pertemuan 6
        binding.btnPertemuan6.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Logout
        binding.btnMenuLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout")
                .setMessage("Yakin ingin keluar?")
                .setPositiveButton("Iya") { _, _ ->
                    // Membersihkan session jika perlu sebelum finish
                    requireActivity().finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}