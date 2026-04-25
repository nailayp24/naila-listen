package com.example.naila_listen.pertemuan_4 // Sesuaikan package!

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.databinding.ActivityCustomSatuBinding

class CustomSatuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomSatuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomSatuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangkap dan menampilkan Data dari Intent
        binding.tvTitleHalaman.text = intent.getStringExtra("JUDUL")
        binding.tvDescHalaman.text = intent.getStringExtra("DESC")
    }
}