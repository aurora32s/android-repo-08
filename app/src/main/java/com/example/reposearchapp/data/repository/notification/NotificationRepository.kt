package com.example.reposearchapp.data.repository.notification

import com.example.reposearchapp.data.entity.notification.Notification

interface NotificationRepository {
    // 알림 데이터 요청
    suspend fun getNotifications(): List<Notification>

    // thread id를 이용해 특정 알림 제거
    suspend fun readNotificationByThreadId(threadId: Long)
}