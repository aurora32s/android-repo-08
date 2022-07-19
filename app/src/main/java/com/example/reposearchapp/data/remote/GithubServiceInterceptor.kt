package com.example.reposearchapp.data.remote

import com.example.reposearchapp.data.repository.AccessTokenRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class GithubServiceInterceptor @Inject constructor(
    private val accessTokenRepository: AccessTokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        accessTokenRepository.getToken()
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${accessTokenRepository.token}")
            .build()
        println(accessTokenRepository.token)
        return chain.proceed(request)
    }
}