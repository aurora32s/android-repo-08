package com.example.reposearchapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("blog")
    val blog: String,
    @SerialName("email")
    val email: String?,
    @SerialName("followers")
    val followers: Int,
    @SerialName("following")
    val following: Int,
    @SerialName("location")
    val location: String,
    @SerialName("repos_url")
    val reposUrl: String,
    @SerialName("starred_url")
    val starredUrl: String
)