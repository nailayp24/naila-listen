package com.example.naila_listen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logistik")
data class LogistikEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaBarang: String,
    val jumlah: Int,
    val satuan: String
)