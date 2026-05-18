package com.example.naila_listen.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.naila_listen.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

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
        BantuanModel("Tenda Pengungsian Besar", "15 Unit", "https://images.unsplash.com/photo-1510312305653-8ed496efae75?w=400"),
        BantuanModel("Genset Listrik 3000W", "5 Unit", "https://images.unsplash.com/photo-1585909695684-35443f55566c?w=400"),
        BantuanModel("Senter Swat Waterproof", "90 Pcs", "https://images.unsplash.com/photo-1554744512-d6c52df9f1cd?w=400"),
        BantuanModel("Perahu Karet Evakuasi", "4 Unit", "https://images.unsplash.com/photo-1545569341-9eb8b30979d9?w=400"),
        BantuanModel("Rompi Pelampung Safety", "60 Pcs", "https://images.unsplash.com/photo-1572569511254-d8f925fe2cbb?w=400"),
        BantuanModel("HT Komunikasi Alinco", "35 Unit", "https://images.unsplash.com/photo-1549465220-1a8b9238cd48?w=400"),
        BantuanModel("Jas Hujan Ponco Relawan", "110 Pcs", "https://images.unsplash.com/photo-1620164949726-8969e7672ffb?w=400"),
        BantuanModel("Megafon Pengeras Suara", "12 Unit", "https://images.unsplash.com/photo-1534330207526-8e81f10ec6fc?w=400"),
        BantuanModel("Cangkul & Sekop Paket", "40 Set", "https://images.unsplash.com/photo-1530124566582-a618bc2615dc?w=400"),
        BantuanModel("Terpal Plastik Biru 4x6", "75 Lembar", "https://images.unsplash.com/photo-1563206767-5b18f218e8de?w=400")
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