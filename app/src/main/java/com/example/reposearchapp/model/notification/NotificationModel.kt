package com.example.reposearchapp.model.notification

data class NotificationModel(
    val id: String,
    val reason: NotificationReasonType,
    val repositoryTitle: String,
    val title: String,
    val type: String,
    val number: String,
    val threadId: String
)
