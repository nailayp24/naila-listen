package com.example.naila_listen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.naila_listen.Home.HomeFragment
import com.example.naila_listen.About.AboutFragment
import com.example.naila_listen.Profile.ProfileFragment
import com.example.naila_listen.More.MoreFragment // 1. Tambahkan import ini
import com.example.naila_listen.databinding.ActivityBaseBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.about -> replaceFragment(AboutFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                // 2. Tambahkan menu More di sini sesuai ID di menu XML kamu
                R.id.more -> replaceFragment(MoreFragment())
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