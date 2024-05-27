package com.draja.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object NetworkClient {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    fun getNetworkClientInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}