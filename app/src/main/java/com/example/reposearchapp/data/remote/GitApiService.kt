package com.example.reposearchapp.data.remote

import com.example.reposearchapp.data.entity.issue.Issue
import com.example.reposearchapp.data.entity.notification.Notification
import com.example.reposearchapp.data.entity.notification.read.NotificationReadResponse
import com.example.reposearchapp.data.url.Url
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApiService {
    /**
     * state 에 맞는 이슈 정보들 요청
     */
    @GET(Url.GET_ISSUES)
    suspend fun getIssues(@Query("state") state: String): Response<List<Issue>>

    /**
     * 알림 데이터 전체 요청
     */
    @GET(Url.GET_NOTIFICATIONS)
    suspend fun getNotifications(): Response<List<Notification>>

    @PATCH("/notifications/threads/{thread_id")
    suspend fun readNotification(@Path("thread_id") threadId: Long): Response<NotificationReadResponse>
}