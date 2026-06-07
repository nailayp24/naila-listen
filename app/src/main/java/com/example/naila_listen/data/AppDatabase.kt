package com.example.naila_listen.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.naila_listen.data.dao.LaporanDao
import com.example.naila_listen.data.dao.LogistikDao
import com.example.naila_listen.data.entity.LaporanEntity
import com.example.naila_listen.data.entity.LogistikEntity

// entities hanya berisi Laporan dan Logistik, versi kita naikkan ke 3
@Database(entities = [LaporanEntity::class, LogistikEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun laporanDao(): LaporanDao
    abstract fun logistikDao(): LogistikDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}