package com.example.naila_listen.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.MainActivity
import com.example.naila_listen.databinding.ActivityThirdResultBinding

class ThirdResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FIX ANTI-CRASH: Mengubah gambar sukses ke logobencana bawaan project yang sudah pasti ada
        binding.ivSuccess.setImageResource(resources.getIdentifier("logobencana", "drawable", packageName))

        Toast.makeText(this, "Sinkronisasi Akun Relawan Berhasil!", Toast.LENGTH_SHORT).show()

        // Trik Delay Otomatis: Menampilkan halaman sukses selama 2.5 detik lalu langsung lempar ke Dashboard Utama
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }, 2500)
    }
}