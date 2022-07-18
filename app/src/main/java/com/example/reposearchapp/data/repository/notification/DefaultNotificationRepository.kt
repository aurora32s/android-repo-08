package com.example.reposearchapp.data.repository.notification

import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.entity.notification.Notification
import com.example.reposearchapp.data.remote.GitApiService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.di.provideGitApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DefaultNotificationRepository(
    private val gitApiService: GitApiService = provideGitApiService(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NotificationRepository {

    /**
     * 특정 사용자의 알림 데이터 요청
     */
    override suspend fun getNotifications(): List<Notification> {
        return when (val result = safeApiCall { gitApiService.getNotifications() }) {
            is Result.Error -> throw Exception(result.exception)
            is Result.Success -> result.data
        }
    }
}