package com.example.reposearchapp.data.repository.issue

import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.entity.issue.Issue
import com.example.reposearchapp.data.remote.GitApiService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.di.provideGitApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultIssueRepository(
    private val gitApiService: GitApiService = provideGitApiService(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IssueRepository {

    /**
     * state 에 따른 issues 요청
     * state : open, closed, all
     */
    override suspend fun getIssues(state: String): List<Issue> {
        return when (val result = safeApiCall { gitApiService.getIssues(state = state) }) {
            is Result.Error -> throw Exception(result.exception)
            is Result.Success -> result.data
        }
    }

}