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
            // Mengambil data penampung dari register_pref
            val sharedPref = getSharedPreferences("register_pref", Context.MODE_PRIVATE)
            val nama = sharedPref.getString("nama", "") ?: ""
            val email = sharedPref.getString("email", "") ?: ""
            val tglLahir = sharedPref.getString("tanggal_lahir", "") ?: ""
            val gender = sharedPref.getString("jenis_kelamin", "") ?: ""
            val username = sharedPref.getString("username", "") ?: ""
            val password = sharedPref.getString("password", "") ?: ""
            val confirmPassword = sharedPref.getString("confirm_password", "") ?: ""

            // Cek apakah ada field yang kosong kosong
            if (nama.isEmpty() || email.isEmpty() || tglLahir.isEmpty() ||
                gender.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {

                Toast.makeText(this, "Validasi Gagal: Semua data wajib diisi!", Toast.LENGTH_SHORT).show()

                // CUKUP PAKAI BARIS INI SAJA (Baris PIVOT_X_IN_DEGREES tadi dihapus saja)
                binding.btnKembali.visibility = View.VISIBLE

                return@setOnClickListener
            }

            // Validasi kecocokan sandi
            if (password != confirmPassword) {
                Toast.makeText(this, "Validasi Gagal: Password tidak cocok!", Toast.LENGTH_SHORT).show()
                binding.btnKembali.visibility = View.VISIBLE
                return@setOnClickListener
            }

            // Jika sukses, simpan kredensial utama ke user_pref untuk login
            val loginPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val loginEditor = loginPref.edit()
            loginEditor.putString("saved_username", username)
            loginEditor.putString("saved_password", password)
            loginEditor.apply()

            Toast.makeText(this, "Validasi Berhasil! Akun siap digunakan.", Toast.LENGTH_LONG).show()

            // FIX AMAN: Dialihkan langsung menuju halaman sukses ThirdResultActivity setelah register berhasil
            val intent = Intent(this, ThirdResultActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.btnKembali.setOnClickListener {
            finish() // Menutup halaman validasi dan kembali ke borang registrasi
        }
    }
}