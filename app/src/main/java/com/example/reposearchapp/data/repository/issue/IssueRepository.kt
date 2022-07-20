package com.example.reposearchapp.data.repository.issue

import androidx.paging.*
import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.entity.issue.Issue
import com.example.reposearchapp.data.remote.GitApiService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.di.provideGitApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IssueRepository(
    private val gitApiService: GitApiService = provideGitApiService()
) {

    /**
     * state 에 따른 issues 요청
     * state : open, closed, all
     */
    fun getIssuesByPaging(): Flow<PagingData<Issue>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { IssuePagingSource(gitApiService) }
        ).flow
    }
}