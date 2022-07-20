package com.example.reposearchapp.data.repository.issue

import androidx.paging.*
import com.example.reposearchapp.data.entity.issue.Issue
import com.example.reposearchapp.data.remote.GitApiService
import com.example.reposearchapp.di.provideGitApiService
import kotlinx.coroutines.flow.Flow

class IssueRepository(
    private val gitApiService: GitApiService = provideGitApiService()
) {

    /**
     * state 에 따른 issues 요청
     * state : open, closed, all
     */
    fun getIssuesByPaging(state: String): Flow<PagingData<Issue>> {
        return Pager(
            config = PagingConfig(pageSize = PER_PAGE),
            pagingSourceFactory = { IssuePagingSource(gitApiService, state, PER_PAGE) }
        ).flow
    }

    companion object {
        const val PER_PAGE = 20
    }
}