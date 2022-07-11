package com.example.reposearchapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {
    /**
     * 테스트
     */
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<User>
}