package com.example.reposearchapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoResponse(
    @SerialName("total_count")
    val totalCount: Int,
    val items: List<Item>
)