package com.example.naila_listen.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// Import ini otomatis menyesuaikan dengan package utama project kamu
import com.example.naila_listen.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup ViewBinding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Logika klik tombol Sign In (Login)
        binding.btnKirim.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()

            if (username.isNotEmpty() && pass.isNotEmpty()) {
                Toast.makeText(this, "Login berhasil, selamat datang $username!", Toast.LENGTH_SHORT).show()

                // UBAH DISINI: Arahkan ke MainActivity (Dashboard), bukan ThirdResultActivity
                val intent = Intent(this, com.example.naila_listen.MainActivity::class.java)
                startActivity(intent)

                // Tambahkan finish() agar user tidak bisa kembali ke login saat menekan tombol back
                finish()
            } else {
                Toast.makeText(this, "Username dan Password jangan dikosongin ya!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}