package com.example.reposearchapp.data.repository.notification

import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.entity.notification.Notification
import com.example.reposearchapp.data.remote.GitApiService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.di.provideGitApiService
import kotlinx.coroutines.*
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
            is Result.Success -> {
                // 각 알림별 댓글 개수 별도 요청
                coroutineScope {
                    result.data.map { noti ->
                        launch {
                            noti.subject.url?.let { url ->
                                noti.commentsNum = getSubject(url)
                            }
                        }
                    }.joinAll()
                }
                result.data
            }
        }
    }

    private suspend fun getSubject(subjectUrl: String): Int {
        return when (val result = safeApiCall { gitApiService.getSubjectByUrl(subjectUrl) }) {
            is Result.Error -> 0
            is Result.Success -> result.data.commentNum
        }
    }

    /**
     * thread id를 이용해 특정 알림 제거
     */
    override suspend fun readNotificationByThreadId(threadId: Long) {
        val result = gitApiService.readNotification(threadId)

        if (result.code() != 205) {
            throw Exception(result.message())
        }
    }
}