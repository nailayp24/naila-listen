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
import com.example.naila_listen.data.entity.LogistikEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogistikAdapter(private var list: List<LogistikEntity>) :
    RecyclerView.Adapter<LogistikAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvContent: TextView = view.findViewById(R.id.tvContent)
        // 🌟 DAFTARKAN ICON TOMBOL HAPUS TEMPAT SAMPAH UNTUK LOGISTIK
        val btnDelete: ImageView = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false) // Menggunakan layout komponen item yang sama
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val context = holder.itemView.context

        holder.tvTitle.text = item.namaBarang
        holder.tvContent.text = "Jumlah: ${item.jumlah} ${item.satuan}"

        // 🌟 AKSI KLIK TOMBOL HAPUS DATA LOGISTIK
        holder.btnDelete.setOnClickListener {
            val db = AppDatabase.getInstance(context)

            // Jalankan coroutine di background thread (IO) untuk menghapus data dari Room
            CoroutineScope(Dispatchers.IO).launch {
                db.logistikDao().deleteLogistik(item)

                // Ambil ulang list data logistik paling segar dari database
                val dataTerbaru = db.logistikDao().getAllLogistik()

                // Kembali ke Main Thread untuk memperbarui antarmuka layar RecyclerView
                withContext(Dispatchers.Main) {
                    list = dataTerbaru
                    notifyDataSetChanged() // Perintahkan adapter untuk menggambar ulang list terbaru
                    Toast.makeText(context, "Data logistik berhasil dihapus!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size
}