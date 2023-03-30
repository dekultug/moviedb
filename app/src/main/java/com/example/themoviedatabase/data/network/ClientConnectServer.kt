package com.example.themoviedatabase.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ClientConnectServer {

    private fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val builder = OkHttpClient.Builder()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
        return builder.build()
    }

    fun <T> createService(service: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()

        return retrofit.create(service)
    }
}
