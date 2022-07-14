package com.example.reposearchapp.data.repository.notification

import com.example.reposearchapp.data.entity.notification.Notification

interface NotificationRepository {
    // notification 데이터 요청
    suspend fun getNotifications(): List<Notification>
}