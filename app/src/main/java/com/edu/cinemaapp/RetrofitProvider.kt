package com.edu.cinemaapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private const val BASE_URL = "https://05e6d8c9-fa5a-4a6d-9e39-db08520a1594.mock.pstmn.io/"
    val apiService: ApicService by lazy { createApiService() }

    private fun createApiService(): ApicService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApicService::class.java)
    }
}