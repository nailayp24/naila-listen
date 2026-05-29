package com.example.naila_listen.Home

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class LogistikFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()

        // Buat layout penampung utama
        val rootLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(16, 16, 16, 16)
        }

        // Buat komponen text secara aman dan presisi
        val titleText = TextView(context).apply {
            text = "📦 Konten Manajemen Logistik Terpadu\n(Data Sembako & Obat-obatan Siap Salur)"
            textSize = 16f // Mengatur ukuran teks berbentuk Float
            setTypeface(null, Typeface.BOLD) // FIX: Cara pasang tebal (Bold) yang benar di Kotlin
            gravity = Gravity.CENTER
            setTextColor(Color.parseColor("#444444"))
        }

        rootLayout.addView(titleText)
        return rootLayout
    }
}