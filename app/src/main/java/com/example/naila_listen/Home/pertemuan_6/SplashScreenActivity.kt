package com.example.naila_listen.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.Home.pertemuan_3.ThirdActivity
import com.example.naila_listen.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Splash screen 3 detik langsung mengunci tujuan ke halaman Login (ThirdActivity)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}