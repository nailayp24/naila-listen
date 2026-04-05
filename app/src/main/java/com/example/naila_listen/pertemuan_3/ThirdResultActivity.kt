package com.example.naila_listen.pertemuan_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.databinding.ActivityThirdResultBinding

class ThirdResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout menggunakan ViewBinding
        binding = ActivityThirdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Halaman ini menampilkan teks "Login Berhasil" yang sudah kita buat di XML
    }
}