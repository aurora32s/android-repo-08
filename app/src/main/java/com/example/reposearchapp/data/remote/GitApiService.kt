package com.example.reposearchapp.data.remote

import com.example.reposearchapp.data.entity.issue.Issue
import com.example.reposearchapp.data.entity.notification.Notification
import com.example.reposearchapp.data.entity.notification.read.NotificationReadResponse
import com.example.reposearchapp.data.entity.subject.Subject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GitApiService {
    /**
     * state 에 맞는 이슈 정보들 요청
     */
    @GET("/issues")
    suspend fun getIssues(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
    ): Response<List<Issue>>

    /**
     * 알림 데이터 전체 요청
     */
    @GET("/notifications")
    suspend fun getNotifications(@Query("per_page") perPage: Int = 20): Response<List<Notification>>

    /**
     * 알림 읽음 처리 요청
     */
    @PATCH("/notifications/threads/{thread_id}")
    suspend fun readNotification(@Path("thread_id") threadId: Long): Response<NotificationReadResponse>

    /**
     * url 을 이용해 특정 subject(issue, PR 등등) 정보 요청
     */
    @GET
    suspend fun getSubjectByUrl(@Url subjectUrl: String): Response<Subject>
}