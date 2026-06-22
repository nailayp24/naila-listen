package com.example.naila_listen.Home.feature_camera_qr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarThirteenth)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarThirteenth.setNavigationOnClickListener { finish() }

        val adapter = ThirteenthTabsAdapter(this)
        binding.viewPagerThirteenth.adapter = adapter

        TabLayoutMediator(binding.tabLayoutThirteenth, binding.viewPagerThirteenth) { tab, position ->
            tab.text = when (position) {
                0 -> "Ambil Foto"
                1 -> "Scan QR"
                else -> "Buat QR"
            }
        }.attach()
    }
}