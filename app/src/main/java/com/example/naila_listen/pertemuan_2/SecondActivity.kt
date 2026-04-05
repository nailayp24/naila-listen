package com.example.naila_listen

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        // Binding View
        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val btnHitungSegitiga = findViewById<Button>(R.id.btnHitungSegitiga)
        val tvHasilSegitiga = findViewById<TextView>(R.id.tvHasilSegitiga)

        val etSisi = findViewById<EditText>(R.id.etSisi)
        val btnHitungKubus = findViewById<Button>(R.id.btnHitungKubus)
        val tvHasilKubus = findViewById<TextView>(R.id.tvHasilKubus)

        // Log untuk memantau siklus hidup activity
        Log.d("Prak-Android", "Activity naila-listen initiated")

        // Logika Hitung Segitiga
        btnHitungSegitiga.setOnClickListener {
            val alas = etAlas.text.toString().toDoubleOrNull() ?: 0.0
            val tinggi = etTinggi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = 0.5 * alas * tinggi
            tvHasilSegitiga.text = "Hasil Luas: $hasil"
            Log.i("HasilHitung", "Luas Segitiga: $hasil")
        }

        // Logika Hitung Kubus
        btnHitungKubus.setOnClickListener {
            val sisi = etSisi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = sisi * sisi * sisi
            tvHasilKubus.text = "Hasil Volume: $hasil"
            Log.i("HasilHitung", "Volume Kubus: $hasil")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}