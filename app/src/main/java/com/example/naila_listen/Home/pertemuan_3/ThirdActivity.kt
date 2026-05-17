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
            val username = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()

            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val registeredUser = sharedPref.getString("saved_username", "")
            val registeredPass = sharedPref.getString("saved_password", "")

            // Pengecekan multi-kondisi login
            if (username == pass && username.isNotEmpty()) {
                goToDashboard()
            } else if (username == registeredUser && pass == registeredPass && username.isNotEmpty()) {
                goToDashboard()
            } else {
                Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun goToDashboard() {
        Toast.makeText(this, "Login Berhasil, Selamat Bertugas!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}