package com.example.naila_listen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.naila_listen.databinding.ActivityMainBinding
import com.example.naila_listen.pertemuan_2.SecondActivity
import com.example.naila_listen.pertemuan_3.ThirdActivity
import com.example.naila_listen.pertemuan_6.WebViewActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // 1. Tombol Kalkulator (P2)
        binding.btnMenuRumus.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        // 2. Tombol Portal Web SIGANA (P5/P6 WebView)
        binding.btnMenuCustom1.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        // 3. Tombol Logout (Materi P6 - SharedPref)
        binding.btnMenuLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah adek yakin ingin keluar dari SIGANA?")
                .setPositiveButton("Iya") { _, _ ->
                    sharedPref.edit {
                        clear()
                        apply()
                    }
                    val intent = Intent(this, ThirdActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}