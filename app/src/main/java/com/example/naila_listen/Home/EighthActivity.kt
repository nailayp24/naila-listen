package com.example.naila_listen.Home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.databinding.ActivityEighthBinding
import com.google.android.material.chip.Chip

class EighthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEighthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEighthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedId = checkedIds.firstOrNull()
            if (selectedId != null) {
                val chip = group.findViewById<Chip>(selectedId)
                Toast.makeText(this, "Pilihan: ${chip?.text}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSubmit.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isNotEmpty()) {
                Toast.makeText(this, "Laporan $email Terkirim!", Toast.LENGTH_LONG).show()
            } else {
                binding.tlEmail.error = "Email wajib diisi!"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}