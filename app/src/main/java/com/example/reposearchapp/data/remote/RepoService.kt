package com.example.reposearchapp.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RepoService {
    private const val BASE_URL = "https://api.github.com/"
    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
        .baseUrl(BASE_URL)
        .build()

    val api: RepoApi = retrofit.create(RepoApi::class.java)
}