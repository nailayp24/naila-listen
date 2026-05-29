package com.example.naila_listen.Home.pertemuan_3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            val nama = binding.etRegNama.text.toString()
            val email = binding.etRegEmail.text.toString()
            val username = binding.etRegUsername.text.toString()
            val password = binding.etRegPassword.text.toString()
            val confirmPassword = binding.etRegConfirmPassword.text.toString()

            // Mengambil data dari DatePicker
            val day = binding.datePickerReg.dayOfMonth
            val month = binding.datePickerReg.month + 1
            val year = binding.datePickerReg.year
            val tanggalLahir = "$day-$month-$year"

            // FIX BENAR: Menggunakan binding.root untuk mencari RadioButton yang terpilih secara aman
            val selectedGenderId = binding.rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                binding.root.findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                ""
            }

            // Menyimpan ke SharedPreferences untuk proses Validasi
            val sharedPref = getSharedPreferences("register_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("nama", nama)
            editor.putString("email", email)
            editor.putString("tanggal_lahir", tanggalLahir)
            editor.putString("jenis_kelamin", gender)
            editor.putString("username", username)
            editor.putString("password", password)
            editor.putString("confirm_password", confirmPassword)
            editor.apply()

            // Berpindah ke Halaman Validasi Data
            startActivity(Intent(this, ValidationActivity::class.java))
        }
    }
}