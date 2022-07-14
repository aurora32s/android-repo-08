package com.example.reposearchapp.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class RepoResponse(
    val total_count: Int,
    val items: List<Item>
)