package com.example.reposearchapp.di

import com.example.reposearchapp.data.remote.GitApiService
import com.example.reposearchapp.data.url.Url
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

fun provideGitApiService(retrofit: Retrofit = provideGitRetrofit()): GitApiService {
    return retrofit.create(GitApiService::class.java)
}

fun provideGitRetrofit(
    okHttpClient: OkHttpClient = buildOkhttpClient(),
    jsonConverterFactory: Converter.Factory = provideJsonConvertFactory()
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.BASE_URL)
        .addConverterFactory(jsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideJsonConvertFactory(): Converter.Factory {
    return Json {
        ignoreUnknownKeys = true
    }.asConverterFactory(MediaType.parse("application/json")!!);
}

fun buildOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Authorization", "Bearer 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")
                .addHeader("Accept", "application/json")
                .build()
            it.proceed(request)
        }
        .build()
}