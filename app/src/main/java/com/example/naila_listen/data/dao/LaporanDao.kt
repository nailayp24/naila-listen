package com.example.naila_listen.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.naila_listen.data.entity.LaporanEntity

@Dao
interface LaporanDao {
    @Query("SELECT * FROM laporan")
    suspend fun getAllLaporan(): List<LaporanEntity>

    @Insert
    suspend fun insertLaporan(laporan: LaporanEntity)

    @Delete
    suspend fun deleteLaporan(laporan: LaporanEntity)
}