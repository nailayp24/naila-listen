package com.example.naila_listen.pertemuan_4 // Sesuaikan package!

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.databinding.ActivityCustomDuaBinding

class CustomDuaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomDuaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomDuaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangkap dan menampilkan Data dari Intent
        binding.tvTitleHalaman.text = intent.getStringExtra("JUDUL")
        binding.tvDescHalaman.text = intent.getStringExtra("DESC")
    }
}