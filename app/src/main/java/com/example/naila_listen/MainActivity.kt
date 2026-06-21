package com.example.naila_listen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.naila_listen.Home.HomeFragment
import com.example.naila_listen.About.AboutFragment
import com.example.naila_listen.Profile.ProfileFragment
import com.example.naila_listen.Note.FragmentLaporan   // Import Menu Room 1 (Laporan Bencana)
import com.example.naila_listen.Note.FragmentLogistik  // Import Menu Room 2 (Logistik Bantuan)
import com.example.naila_listen.databinding.ActivityBaseBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🌟 CEK APAKAH DIBUKA LEWAT NOTIFIKASI REMINDER LOGISTIK 🌟
        if (savedInstanceState == null) {
            val openFragment = intent.getStringExtra("OPEN_FRAGMENT")
            if (openFragment == "LAPORAN") {
                replaceFragment(FragmentLaporan())
            } else if (openFragment == "LOGISTIK" || intent.hasExtra("target_activity")) {
                replaceFragment(FragmentLogistik()) // Langsung lompat ke halaman Logistik yang relevan
            } else {
                replaceFragment(HomeFragment())
            }
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.about -> replaceFragment(AboutFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.more -> replaceFragment(FragmentLaporan())
                R.id.note -> replaceFragment(FragmentLogistik())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}