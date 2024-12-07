package com.example.myapplication.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import okhttp3.OkHttpClient
import okhttp3.Credentials

object ApiServices {
    private const val BASE_URL = "http://automacorp.devmind.cleverapps.io/api/"
    private const val USERNAME = "user"
    private const val PASSWORD = "password"

    val roomsApiService: RoomsApiService by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", Credentials.basic(USERNAME, PASSWORD))
                    .build()
                chain.proceed(request)
            }
            .build()

        Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RoomsApiService::class.java)
    }
}
