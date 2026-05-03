package com.example.naila_listen.Home.pertemuan_6 // Update package-nya

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.naila_listen.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar SIGANA
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "SIGANA Desktop"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("https://nailayop-sib.alwaysdata.net/dashboard")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}