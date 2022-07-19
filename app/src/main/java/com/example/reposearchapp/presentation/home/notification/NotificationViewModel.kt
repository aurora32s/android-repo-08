package com.example.reposearchapp.presentation.home.notification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reposearchapp.R
import com.example.reposearchapp.data.entity.notification.Notification
import com.example.reposearchapp.data.repository.notification.DefaultNotificationRepository
import com.example.reposearchapp.data.repository.notification.NotificationRepository
import com.example.reposearchapp.model.notification.NotificationModel
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val notificationRepository: NotificationRepository = DefaultNotificationRepository()
) : ViewModel() {

    private val _notificationStateLiveData =
        MutableLiveData<NotificationState>(NotificationState.UnInitialState)
    val notificationStateLiveData: LiveData<NotificationState>
        get() = _notificationStateLiveData

    fun fetchData() = viewModelScope.launch {
        try {
            _notificationStateLiveData.value = NotificationState.Loading
            val notifications = notificationRepository.getNotifications()

            if (notifications.isNotEmpty()) {
                _notificationStateLiveData.value =
                    NotificationState.Success(notifications.map { it.toModel() })
            }
        } catch (exception: Exception) {
            _notificationStateLiveData.value =
                NotificationState.Error(R.string.error_notification_list)
        }
    }

    fun readNotification(notificationModel: NotificationModel) = viewModelScope.launch {
        try {
            when (val state = _notificationStateLiveData.value) {
                is NotificationState.Success -> {
                    val threadId = notificationModel.threadId
                    notificationRepository.readNotificationByThreadId(threadId)
                    _notificationStateLiveData.value = NotificationState.Success(
                        state.notifications.filter { it.id != notificationModel.id }
                    )
                }
                else -> {}
            }
        } catch (exception: Exception) {
            _notificationStateLiveData.value =
                NotificationState.ErrorNotificationRead(R.string.error_notification_read)
        }
    }
}