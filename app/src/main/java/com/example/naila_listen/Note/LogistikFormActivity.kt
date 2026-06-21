package com.example.naila_listen.Note

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naila_listen.MainActivity
import com.example.naila_listen.data.AppDatabase
import com.example.naila_listen.data.entity.LogistikEntity
import com.example.naila_listen.databinding.ActivityLogistikFormBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.naila_listen.utils.PermissionHelper
import com.example.naila_listen.utils.ReminderHelper

class LogistikFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogistikFormBinding
    private lateinit var db: AppDatabase

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Izin Notifikasi Aktif", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Izin Notifikasi Ditolak", Toast.LENGTH_SHORT).show()
            }
        }

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

        // Jalankan pengecekan runtime permission untuk Android 13+
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

        binding.btnSaveLogistik.setOnClickListener {
            // 🌟 KITA PANGGIL LANGSUNG ID EDITTEXT-NYA TANPA MENGGUNAKAN TEXTINPUTLAYOUT
            val nama = binding.etNamaBarang.text.toString().trim()
            val jml = binding.etJumlah.text.toString().trim()
            val satuan = binding.etSatuan.text.toString().trim()
            val menitInput = binding.etMenitReminder.text.toString().trim() // 🌟 AMBIL NILAI DINAMIS DARI USER

            if (nama.isNotEmpty() && jml.isNotEmpty() && satuan.isNotEmpty() && menitInput.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    db.logistikDao().insertLogistik(
                        LogistikEntity(
                            namaBarang = nama,
                            jumlah = jml.toInt(),
                            satuan = satuan
                        )
                    )

                    // 🌟 DAFTARKAN REAL-TIME ALARM PENGINGAT DENGAN INPUT USER 🌟
                    val totalMenit = menitInput.toInt()
                    ReminderHelper.setReminder(
                        context = this@LogistikFormActivity,
                        minutesFromNow = totalMenit,
                        title = "Stok Logistik Masuk: $nama",
                        message = "Waktunya memantau pembagian bantuan $nama ($jml $satuan) ke posko warga.",
                        targetActivity = MainActivity::class.java
                    )

                    // Kembali ke Main Thread untuk menutup halaman dan menampilkan Toast sukses
                    runOnUiThread {
                        Toast.makeText(this@LogistikFormActivity, "Data Logistik & Reminder $totalMenit Menit Aktif!", Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Semua data form termasuk menit wajib diisi ya!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}