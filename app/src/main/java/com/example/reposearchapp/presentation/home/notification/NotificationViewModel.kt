package com.example.reposearchapp.presentation.home.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.reposearchapp.R
import com.example.reposearchapp.data.repository.notification.NotificationRepository
import com.example.reposearchapp.model.notification.NotificationModel
import com.example.reposearchapp.presentation.home.issue.IssueState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val notificationRepository: NotificationRepository = NotificationRepository()
) : ViewModel() {

    private val _notificationStateLiveData =
        MutableLiveData<NotificationState>(NotificationState.UnInitialState)
    val notificationStateLiveData: LiveData<NotificationState>
        get() = _notificationStateLiveData

    lateinit var notificationDataFlow: Flow<PagingData<NotificationModel>>
        private set


    fun getNotifications() = viewModelScope.launch {
        notificationDataFlow = notificationRepository.getNotifications().cachedIn(viewModelScope)
            .map { notifications -> notifications.map { notification -> notification.toModel() } }
            .onStart { _notificationStateLiveData.value = NotificationState.Loading }
            .catch {
                _notificationStateLiveData.value =
                    NotificationState.Error(R.string.error_issue_list)
            }
        _notificationStateLiveData.value = NotificationState.FetchFinish
    }

    fun readNotification(notificationIndex: Int) = viewModelScope.launch {
        try {
            when (notificationStateLiveData.value) {
                is NotificationState.FetchFinish -> {
//                    val threadId = notificationModel.threadId
//                    notificationRepository.readNotificationByThreadId(threadId)
//                    notificationDataFlow =
//                        notificationDataFlow.map { notifications -> notifications.filter { it.id != notificationModel.id } }
                }
                else -> {}
            }
        } catch (exception: Exception) {
            _notificationStateLiveData.value =
                NotificationState.ErrorNotificationRead(R.string.error_notification_read)
        }
    }
}