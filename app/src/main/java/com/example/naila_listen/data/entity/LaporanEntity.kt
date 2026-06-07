    package com.example.naila_listen.data.entity

    import androidx.room.Entity
    import androidx.room.PrimaryKey

    @Entity(tableName = "laporan")
    data class LaporanEntity(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val jenisBencana: String,
        val lokasi: String,
        val deskripsi: String
    )