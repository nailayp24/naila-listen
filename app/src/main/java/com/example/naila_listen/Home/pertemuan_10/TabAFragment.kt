package com.example.naila_listen.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.naila_listen.databinding.FragmentTabCBinding

class TabAFragment : Fragment() {

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
        BantuanModel("Beras Premium 10kg", "150 Karung", "https://images.unsplash.com/photo-1586201375761-83865001e31c?w=400"),
        BantuanModel("Mie Instan Kaldu Ayam", "320 Kardus", "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?w=400"),
        BantuanModel("Air Mineral Botol 600ml", "500 Karton", "https://images.unsplash.com/photo-1608885898957-a599fb1698d6?w=400"),
        BantuanModel("Suku Cadang Tenda", "80 Unit", "https://images.unsplash.com/photo-1504280390367-361c6d9f38f4?w=400"),
        BantuanModel("Sarden Kaleng Gurih", "450 Kaleng", "https://images.unsplash.com/photo-1599599810769-bcde5a160d32?w=400"),
        BantuanModel("Biskuit Protein Tinggi", "200 Dus", "https://images.unsplash.com/photo-1558961363-fa8fdf82db35?w=400"),
        BantuanModel("Minyak Goreng 2 Liter", "180 Pouch", "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?w=400"),
        BantuanModel("Telur Ayam Ras", "100 Peti", "https://images.unsplash.com/photo-1506976785307-8732e854ad03?w=400"),
        BantuanModel("Gula Pasir 1kg", "140 Bungkus", "https://images.unsplash.com/photo-1581783898377-1c85bf937427?w=400"),
        BantuanModel("Bubur Bayi Instan", "95 Kotak", "https://images.unsplash.com/photo-1592417817098-8f3d6eb19675?w=400")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BantuanAdapter(productList) { item ->
            Toast.makeText(requireContext(), "Terpilih: ${item.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter

            isNestedScrollingEnabled = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}