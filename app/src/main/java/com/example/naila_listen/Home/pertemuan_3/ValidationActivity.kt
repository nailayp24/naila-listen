package com.example.naila_listen.Home.pertemuan_3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.databinding.ActivityValidationBinding

class ValidationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityValidationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmitValidation.setOnClickListener {
            val sharedPref = getSharedPreferences("register_pref", Context.MODE_PRIVATE)
            val nama = sharedPref.getString("nama", "") ?: ""
            val email = sharedPref.getString("email", "") ?: ""
            val tglLahir = sharedPref.getString("tanggal_lahir", "") ?: ""
            val gender = sharedPref.getString("jenis_kelamin", "") ?: ""
            val username = sharedPref.getString("username", "") ?: ""
            val password = sharedPref.getString("password", "") ?: ""
            val confirmPassword = sharedPref.getString("confirm_password", "") ?: ""

            if (nama.isEmpty() || email.isEmpty() || tglLahir.isEmpty() ||
                gender.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {

                Toast.makeText(this, "Validasi Gagal: Semua data wajib diisi!", Toast.LENGTH_SHORT).show()
                binding.btnKembali.visibility = View.VISIBLE
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Validasi Gagal: Password tidak cocok!", Toast.LENGTH_SHORT).show()
                binding.btnKembali.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val loginPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val loginEditor = loginPref.edit()
            loginEditor.putString("saved_username", username)
            loginEditor.putString("saved_password", password)
            loginEditor.apply()

            Toast.makeText(this, "Validasi Berhasil! Akun siap digunakan.", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ThirdActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        binding.btnKembali.setOnClickListener {
            finish()
        }
    }
}