package com.example.naila_listen.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naila_listen.data.AppDatabase
import com.example.naila_listen.data.entity.LaporanEntity
import com.example.naila_listen.databinding.ActivityLaporanFormBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaporanFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaporanFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarLaporan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarLaporan.setNavigationOnClickListener { finish() }

        db = AppDatabase.getInstance(this)

        binding.btnSaveLaporan.setOnClickListener {
            val jenis = binding.etJenisBencana.text.toString().trim()
            val lokasi = binding.etLokasiLaporan.text.toString().trim()
            val deskripsi = binding.etDeskripsiLaporan.text.toString().trim()

            if (jenis.isNotEmpty() && lokasi.isNotEmpty() && deskripsi.isNotEmpty()) {
                // 🌟 GUNAKAN DISPATCHERS.IO UNTUK MEMAKSA PROSES INSERT MASUK DATABASE BACKGROUND
                lifecycleScope.launch(Dispatchers.IO) {
                    val laporanBaru = LaporanEntity(
                        jenisBencana = jenis,
                        lokasi = lokasi,
                        deskripsi = deskripsi
                    )
                    db.laporanDao().insertLaporan(laporanBaru)

                    // Kembali ke Main Thread untuk menutup halaman dan menampilkan Toast sukses
                    runOnUiThread {
                        Toast.makeText(this@LaporanFormActivity, "Laporan berhasil direkam lokal!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Semua kolom form wajib diisi ya!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}