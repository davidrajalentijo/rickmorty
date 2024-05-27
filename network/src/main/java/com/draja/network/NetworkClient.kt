package com.draja.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object NetworkClient {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    fun getNetworkClientInstance(baseUrl: String?): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl ?: BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}