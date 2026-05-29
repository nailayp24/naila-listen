package com.example.naila_listen.data.api

import com.example.naila_listen.data.model.EarthquakeResponse
import com.example.naila_listen.data.model.EarthquakeListResponse
import retrofit2.http.GET

interface BinaDesaApiService {
    // Jalur 1: Memakai EarthquakeResponse (Objek)
    @GET("DataMKG/TEWS/autogempa.json")
    suspend fun getLiveEarthquake(): EarthquakeResponse

    // Jalur 2: Memakai EarthquakeListResponse (List/Array)
    @GET("DataMKG/TEWS/gempaterkini.json")
    suspend fun getEarthquakeList(): EarthquakeListResponse
}