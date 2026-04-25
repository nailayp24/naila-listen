package com.example.naila_listen // Sesuaikan package kamu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.example.naila_listen.databinding.ActivityMainBinding
import com.example.naila_listen.pertemuan_2.SecondActivity
import com.example.naila_listen.pertemuan_3.ThirdActivity
import com.example.naila_listen.pertemuan_4.CustomSatuActivity
import com.example.naila_listen.pertemuan_4.CustomDuaActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol Rumus
        binding.btnMenuRumus.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // 2. Tombol Custom 1 (Kirim Data Buku Atomic Habits)
        binding.btnMenuCustom1.setOnClickListener {
            val intent = Intent(this, CustomSatuActivity::class.java)

            // Kita kirim status bacaan dan judul bukunya
            intent.putExtra("JUDUL", "Currently reading")
            intent.putExtra("DESC", "Atomic Habits")

            startActivity(intent)
        }

        // 3. Tombol Custom 2 (Kirim Data Tema Buku)
        binding.btnMenuCustom2.setOnClickListener {
            val intent = Intent(this, CustomDuaActivity::class.java)
            intent.putExtra("JUDUL", "Trending")
            intent.putExtra("DESC", "Discover the best books of the week.")
            startActivity(intent)
        }

        // 4. Tombol Logout (Fitur Alert & Snackbar)
        binding.btnMenuLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah adek yakin ingin logout?")
                .setPositiveButton("Iya") { _, _ ->
                    val intent = Intent(this, ThirdActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .show()
        }
    }
}