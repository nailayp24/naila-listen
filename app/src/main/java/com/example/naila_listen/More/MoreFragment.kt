package com.example.naila_listen.More

import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.naila_listen.R
import com.example.naila_listen.databinding.FragmentMoreBinding

class MoreFragment : Fragment(R.layout.fragment_more) {
    private lateinit var binding: FragmentMoreBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoreBinding.bind(view)
        binding.toolbar.title = "Pengaturan & Info"

        // Data List (Pertemuan 9)
        val dataMenu = listOf(
            mapOf("title" to "Privacy Policy", "info" to "Ketentuan data warga"),
            mapOf("title" to "About Project", "info" to "SIGANA Bina Desa v1.0"),
            mapOf("title" to "Terms of Service", "info" to "Syarat penggunaan"),
            mapOf("title" to "Help Center", "info" to "Bantuan relawan")
        )

        // Implementasi SimpleAdapter
        val adapter = SimpleAdapter(
            requireContext(), dataMenu,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "info"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewMore.adapter = adapter
        binding.listViewMore.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(requireContext(), "Membuka: ${dataMenu[position]["title"]}", Toast.LENGTH_SHORT).show()
        }
    }
}