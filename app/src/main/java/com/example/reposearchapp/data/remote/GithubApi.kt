package com.example.reposearchapp.data.remote

import com.example.reposearchapp.data.entity.RepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubApi {

    @Headers("Accept: application/json")
    @GET("search/repositories")
    suspend fun getRepos(@Query("q") query: String): Response<RepoResponse>
}