package com.example.reposearchapp.data.repository

import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.entity.UserResponse
import com.example.reposearchapp.data.remote.GithubService
import com.example.reposearchapp.data.safeApiCall

class ProfileRepository {
    /**
     * 로그인한 사용자의 정보 요청
     */
    suspend fun getUser(): UserResponse {
        return when (val result = safeApiCall { GithubService.api.getUser() }) {
            is Result.Success -> result.data
            is Result.Error -> {
                throw Exception(result.exception)
            }
        }
    }
}