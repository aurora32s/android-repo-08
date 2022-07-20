package com.example.reposearchapp.data.repository.notification

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.entity.notification.Notification
import com.example.reposearchapp.data.remote.GitApiService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.di.provideGitApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class NotificationRepository(
    private val gitApiService: GitApiService = provideGitApiService()
) {

    /**
     * 특정 사용자의 알림 데이터 요청
     */
    fun getNotifications(): Flow<PagingData<Notification>> {
        return Pager(
            config = PagingConfig(pageSize = PER_PAGE),
            pagingSourceFactory = { NotificationPagingSource(gitApiService, PER_PAGE) }
        ).flow
    }


    /**
     * thread id를 이용해 특정 알림 제거
     */
    suspend fun readNotificationByThreadId(threadId: Long) {
        val result = gitApiService.readNotification(threadId)
        if (result.code() != 205) {
            throw Exception(result.message())
        }
    }

    companion object {
        const val PER_PAGE = 10
    }
}