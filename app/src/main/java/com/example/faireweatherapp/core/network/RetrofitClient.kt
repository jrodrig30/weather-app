package com.example.faireweatherapp.core.network

import com.example.faireweatherapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    inline fun <reified T> createWebService(): T {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }

    fun provideOkHttpClient() = OkHttpClient.Builder().apply {
        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(logging)
            .connectTimeout(BuildConfig.HTTP_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.HTTP_TIMEOUT, TimeUnit.SECONDS)
    }.build()
}