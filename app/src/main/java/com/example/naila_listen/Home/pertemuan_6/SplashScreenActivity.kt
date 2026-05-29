package com.example.naila_listen.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.Home.onboarding.OnboardingActivity // <--- IMPORT BARU
import com.example.naila_listen.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FIXED: Splash screen muncul 3 detik, lalu melempar ke halaman Onboarding Screen
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnboardingActivity::class.java) // <--- UBAH KE SINI
            startActivity(intent)
            finish() // Menghancurkan Splash Screen agar tidak bisa di-back kembali
        }, 3000)
    }
}