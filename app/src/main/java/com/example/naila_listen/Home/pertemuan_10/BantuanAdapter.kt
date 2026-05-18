package com.example.naila_listen.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.naila_listen.databinding.ItemProductBinding

class BantuanAdapter(
    private val productList: List<BantuanModel>,
    private val onItemClick: (BantuanModel) -> Unit
) : RecyclerView.Adapter<BantuanAdapter.BantuanViewHolder>() {

    inner class BantuanViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BantuanViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BantuanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BantuanViewHolder, position: Int) {
        val item = productList[position]
        with(holder.binding) {
            tvProductName.text = item.name
            tvProductPrice.text = item.price

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgProduct)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = productList.size
}