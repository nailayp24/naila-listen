package com.example.naila_listen.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naila_listen.data.model.EarthquakeModel
import com.example.naila_listen.databinding.ItemNewsBinding

class NewsAdapter(private var newsList: List<EarthquakeModel>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]
        holder.binding.tvItemTitle.text = item.wilayah
        holder.binding.tvItemDetail.text = "Kekuatan: ${item.magnitude} SR | Waktu: ${item.tanggal} (${item.jam})"
        holder.binding.tvItemPotensi.text = "Status: ${item.potensi} (Kedalaman: ${item.kedalaman})"
    }

    override fun getItemCount(): Int = newsList.size

    fun updateData(newList: List<EarthquakeModel>) {
        this.newsList = newList
        notifyDataSetChanged()
    }
}