package com.example.naila_listen.Home.pertemuan_10

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    // Jumlah tab bawah ada 3 (Logistik, Medis, Posko Desa)
    override fun getItemCount(): Int = 3

    // Menghubungkan posisi tab (0, 1, 2) dengan fragment P10 yang kamu buat kemarin
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment() // Menampilkan isi TabAFragment (Logistik)
            1 -> TabBFragment() // Menampilkan isi TabBFragment (Medis)
            2 -> TabCFragment() // Menampilkan isi TabCFragment (Posko Desa)
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}