package com.example.reposearchapp.data.remote

import com.example.reposearchapp.data.entity.search.RepoResponse
import com.example.reposearchapp.data.entity.RepoResponse
import com.example.reposearchapp.data.entity.StarredByUserResponse
import com.example.reposearchapp.data.entity.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Response<RepoResponse>

    @Headers("Accept: application/json")
    @GET("user")
    suspend fun getUser(): Response<UserResponse>

    @GET("/user/starred")
    suspend fun getStarsByUser(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 100
    ): List<StarredByUserResponse>
}