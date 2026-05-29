package com.example.naila_listen.Home.pertemuan_3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.MainActivity
import com.example.naila_listen.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            // Mengambil kredensial akun yang baru divalidasi dari ValidationActivity
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val registeredUser = sharedPref.getString("saved_username", "")
            val registeredPass = sharedPref.getString("saved_password", "")

            // Pengecekan multi-kondisi login secara presisi
            if (username.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Nama Pengguna dan Kata Sandi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else if (username == pass) {
                // Kondisi bypass mode development (jika username & password diisi sama)
                goToDashboard()
            } else if (username == registeredUser && pass == registeredPass) {
                // Kondisi login menggunakan akun relawan yang baru didaftarkan
                goToDashboard()
            } else {
                Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigasi beralih ke halaman pendaftaran relawan baru
        binding.btnToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun goToDashboard() {
        Toast.makeText(this, "Login Berhasil, Selamat Bertugas!", Toast.LENGTH_SHORT).show()

        // Membuka Dashboard Utama SIGANA dan membersihkan tumpukan halaman lama
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}