package com.example.naila_listen.Home.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.naila_listen.Home.pertemuan_3.ThirdActivity
import com.example.naila_listen.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    // Tentukan jumlah halaman total secara konstan
    private val totalPages = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FIX SINKRON: Menginisialisasi adapter tanpa melempar List objek Fragment mentah
        val pagerAdapter = OnboardingTabsAdapter(this, totalPages)
        binding.onboardingViewPager.adapter = pagerAdapter

        // Set indikator awal (halaman pertama aktif = warna oranye)
        updateDotsIndicator(0)

        // Deteksi pergeseran halaman untuk memperbarui warna titik manual
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDotsIndicator(position)

                // Jika berada di halaman terakhir (halaman indeks 2)
                if (position == totalPages - 1) {
                    binding.btnAyoMulai.visibility = View.VISIBLE
                    binding.dotsContainer.visibility = View.INVISIBLE
                } else {
                    binding.btnAyoMulai.visibility = View.INVISIBLE
                    binding.dotsContainer.visibility = View.VISIBLE
                }
            }
        })

        binding.btnAyoMulai.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Fungsi pengubah warna titik secara manual (Bebas Eror Library)
    private fun updateDotsIndicator(position: Int) {
        val activeColor = Color.parseColor("#F57C00")
        val inactiveColor = Color.parseColor("#CCCCCC")

        binding.dot1.setBackgroundColor(if (position == 0) activeColor else inactiveColor)
        binding.dot2.setBackgroundColor(if (position == 1) activeColor else inactiveColor)
        binding.dot3.setBackgroundColor(if (position == 2) activeColor else inactiveColor)
    }
}