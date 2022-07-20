package com.example.reposearchapp.data.entity.subject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    @SerialName("comments")
    val commentNum: Int
)
