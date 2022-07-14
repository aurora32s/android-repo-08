package com.example.reposearchapp.data.remote

import com.example.reposearchapp.data.Token.token
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object GithubService {
    private const val BASE_URL = "https://api.github.com/"
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            it.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
        }.asConverterFactory(MediaType.parse("application/json")!!))
        .baseUrl(BASE_URL)
        .build()

    val api: GithubApi = retrofit.create(GithubApi::class.java)
}