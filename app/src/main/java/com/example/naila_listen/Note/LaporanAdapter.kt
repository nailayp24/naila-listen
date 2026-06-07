package com.example.naila_listen.Note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.naila_listen.R
import com.example.naila_listen.data.AppDatabase
import com.example.naila_listen.data.entity.LaporanEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaporanAdapter(private var list: List<LaporanEntity>) :
    RecyclerView.Adapter<LaporanAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvContent: TextView = view.findViewById(R.id.tvContent)
        // 🌟 DAFTARKAN ICON TOMBOL HAPUS TEMPAT SAMPAH
        val btnDelete: ImageView = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val context = holder.itemView.context

        holder.tvTitle.text = item.jenisBencana
        holder.tvContent.text = "${item.lokasi} - ${item.deskripsi}"

        // 🌟 AKSI KLIK TOMBOL HAPUS DATA
        holder.btnDelete.setOnClickListener {
            // Inisialisasi DB lokal di dalam adapter
            val db = AppDatabase.getInstance(context)

            // Jalankan coroutine background thread untuk menghapus data di Room
            CoroutineScope(Dispatchers.IO).launch {
                db.laporanDao().deleteLaporan(item)

                // Ambil ulang list data terbaru dari database setelah dihapus
                val dataTerbaru = db.laporanDao().getAllLaporan()

                // Pindah ke Main Thread untuk menyegarkan tampilan RecyclerView
                withContext(Dispatchers.Main) {
                    list = dataTerbaru
                    notifyDataSetChanged() // Paksa layar menggambar ulang list terbaru
                    Toast.makeText(context, "Laporan berhasil dihapus!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size
}