package com.example.naila_listen.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naila_listen.BaseActivity
import com.example.naila_listen.MainActivity
import com.example.naila_listen.R
import com.example.naila_listen.Home.pertemuan_3.ThirdActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Ambil data shared preferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(2000) // Delay splash screen 2 detik

            // Alur: Jika sudah login ke Main, jika belum ke Auth (ThirdActivity)
            val intent = if (isLogin) {
                Intent(this@SplashScreenActivity, BaseActivity::class.java)
            } else {
                Intent(this@SplashScreenActivity, ThirdActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }
}