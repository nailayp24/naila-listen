package com.example.naila_listen.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()

            // Simulasi login (Username = Password)
            if (username == pass && username.isNotEmpty()) {
                // SIMPAN SESI LOGIN
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                Toast.makeText(this, "Login Berhasil, Selamat Bertugas!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, com.example.naila_listen.MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Username dan Password salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}