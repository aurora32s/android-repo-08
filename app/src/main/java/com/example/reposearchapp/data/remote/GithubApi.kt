package com.example.reposearchapp.data.remote

import com.example.reposearchapp.data.entity.search.RepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Response<RepoResponse>
}