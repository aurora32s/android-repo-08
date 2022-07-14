package com.example.reposearchapp.data.remote

import com.example.reposearchapp.data.entity.issue.Issue
import com.example.reposearchapp.data.entity.notification.Notification
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApiService {
    /**
     * state 에 맞는 이슈 정보들 요청
     */
    @GET("/issues")
    suspend fun getIssues(@Query("state") state: String): Response<List<Issue>>

    @GET("/notifications")
    suspend fun getNotifications(): Response<List<Notification>>
}