package com.example.naila_listen.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.naila_listen.data.entity.LogistikEntity

@Dao
interface LogistikDao {
    @Query("SELECT * FROM logistik")
    suspend fun getAllLogistik(): List<LogistikEntity>

    @Insert
    suspend fun insertLogistik(logistik: LogistikEntity)

    @Delete
    suspend fun deleteLogistik(logistik: LogistikEntity)
}