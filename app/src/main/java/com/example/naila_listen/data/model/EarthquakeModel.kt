package com.example.naila_listen.data.model

import com.google.gson.annotations.SerializedName

// --- 1. MODEL UNTUK GEMPA UTAMA (AUTOGEMPA - OBJEK TUNGGAL) ---
data class EarthquakeResponse(
    @SerializedName("Infogempa")
    val infoGempa: InfoGempaMain
)

data class InfoGempaMain(
    @SerializedName("gempa")
    val detailGempa: EarthquakeModel?
)

// --- 2. MODEL UNTUK LIST 15 GEMPA (GEMPATERKINI - ARRAY/LIST) ---
data class EarthquakeListResponse(
    @SerializedName("Infogempa")
    val infoGempaList: InfoGempaList // <-- Nama ini harus pas dengan yang dipanggil Fragment
)

data class InfoGempaList(
    @SerializedName("gempa")
    val listGempa: List<EarthquakeModel>?
)

// --- 3. DATA MODEL UTAMA ---
data class EarthquakeModel(
    @SerializedName("Tanggal")
    val tanggal: String,
    @SerializedName("Jam")
    val jam: String,
    @SerializedName("Magnitude")
    val magnitude: String,
    @SerializedName("Kedalaman")
    val kedalaman: String,
    @SerializedName("Wilayah")
    val wilayah: String,
    @SerializedName("Potensi")
    val potensi: String
)