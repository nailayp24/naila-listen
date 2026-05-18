package com.example.naila_listen.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naila_listen.databinding.FragmentTabCBinding

class TabBFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val productList = listOf(
        BantuanModel("Kotak P3K Darurat", "80 Unit", "https://images.unsplash.com/photo-1603398938378-e54eab446dde?w=400"),
        BantuanModel("Selimut Wol Tebal", "450 Lembar", "https://images.unsplash.com/photo-1580301762395-21ce84d00bc6?w=400"),
        BantuanModel("Masker Medis 3-Ply", "1000 Box", "https://images.unsplash.com/photo-1584622650111-993a426fbf0a?w=400"),
        BantuanModel("Pakaian Layak Pakai Dewasa", "250 Paket", "https://images.unsplash.com/photo-1489987707025-afc232f7ea0f?w=400"),
        BantuanModel("Obat Paracetamol 500mg", "300 Strip", "https://images.unsplash.com/photo-1584017911766-d451b3d0e843?w=400"),
        BantuanModel("Tabung Oksigen Portabel", "25 Unit", "https://images.unsplash.com/photo-1581093458791-9f3c3900df4b?w=400"),
        BantuanModel("Tandu Lipat Darurat", "15 Unit", "https://images.unsplash.com/photo-1516574187841-cb9cc2ca948b?w=400"),
        BantuanModel("Popok Bayi Sekali Pakai", "160 Pack", "https://images.unsplash.com/photo-1555252333-9f8e92e65df9?w=400"),
        BantuanModel("Pembalut Wanita", "220 Pack", "https://images.unsplash.com/photo-1563453392212-326f5e854473?w=400"),
        BantuanModel("Sabun Antiseptik Cair", "175 Botol", "https://images.unsplash.com/photo-1601049541289-9b1b7bbbfe19?w=400")
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BantuanAdapter(productList) { item ->
            Toast.makeText(requireContext(), "Terpilih: ${item.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter

            isNestedScrollingEnabled = false

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}