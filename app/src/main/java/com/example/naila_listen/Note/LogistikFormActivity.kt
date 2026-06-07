package com.example.naila_listen.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naila_listen.data.AppDatabase
import com.example.naila_listen.data.entity.LogistikEntity
import com.example.naila_listen.databinding.ActivityLogistikFormBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogistikFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogistikFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogistikFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        setSupportActionBar(binding.toolbarLogistik)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarLogistik.setNavigationOnClickListener {
            finish()
        }

        binding.btnSaveLogistik.setOnClickListener {
            val nama = binding.etNamaBarang.text.toString().trim()
            val jml = binding.etJumlah.text.toString().trim()
            val satuan = binding.etSatuan.text.toString().trim()

            if (nama.isNotEmpty() && jml.isNotEmpty() && satuan.isNotEmpty()) {
                // 🌟 GUNAKAN DISPATCHERS.IO UNTUK MEMAKSA DATA TERSIMPAN DI GROUND DATABASE
                lifecycleScope.launch(Dispatchers.IO) {
                    db.logistikDao().insertLogistik(
                        LogistikEntity(
                            namaBarang = nama,
                            jumlah = jml.toInt(),
                            satuan = satuan
                        )
                    )

                    // Kembali ke Main Thread untuk menutup halaman dan menampilkan Toast sukses
                    runOnUiThread {
                        Toast.makeText(this@LogistikFormActivity, "Data Logistik Tersimpan!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Semua data wajib diisi, Naila!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}