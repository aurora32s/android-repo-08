package com.example.reposearchapp.presentation.home.notification

import com.example.reposearchapp.model.notification.NotificationModel

sealed interface NotificationState {
    object UnInitialState : NotificationState
    object Loading : NotificationState

    data class Success(
        val notifications: List<NotificationModel>
    ) : NotificationState

    data class Error(
        val errorMsg: Int
    ) : NotificationState
}