package com.example.reposearchapp.data.remote

import com.example.reposearchapp.data.entity.issue.Issue
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApiService {

    @GET("/issues")
    suspend fun getIssues(@Query("state") state: String): Response<List<Issue>>
}