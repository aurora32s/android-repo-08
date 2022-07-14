package com.example.reposearchapp.data.repository.issue

import android.util.Log
import com.example.reposearchapp.data.entity.issue.Issue
import com.example.reposearchapp.data.remote.GitApiService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.di.provideGitApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.reposearchapp.data.Result
import com.example.reposearchapp.presentation.home.issue.IssueFragment
import java.lang.Exception

class DefaultIssueRepository(
    private val gitApiService: GitApiService = provideGitApiService(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IssueRepository {

    /**
     * state 에 따른 issues 요청
     * state : open, closed, all
     */
    override suspend fun getIssues(state: String): List<Issue> = withContext(ioDispatcher) {
        val result = safeApiCall {
            gitApiService.getIssues(state = state)
        }

        Log.d(IssueFragment.TAG, result.toString())
        if (result is Result.Success) {
            result.data
        } else {
            throw Exception()
        }
    }

}