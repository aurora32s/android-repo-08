package com.example.reposearchapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reposearchapp.data.entity.Item
import com.example.reposearchapp.data.remote.GithubService
import com.example.reposearchapp.data.repository.NETWORK_PAGE_SIZE

class SearchPagingSource(val query: String) : PagingSource<Int, Item>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val start = params.key ?: STARTING_KEY

        when (val result = safeApiCall { GithubService.api.getRepos(query, start) }) {
            is Result.Success -> {
                return LoadResult.Page(
                    data = result.data.items,
                    prevKey = when (start) {
                        STARTING_KEY -> null
                        else -> start - 1
                    },
                    nextKey = start + (params.loadSize / NETWORK_PAGE_SIZE)
                )
            }

            is Result.Error -> {
                return LoadResult.Error(Exception(result.exception))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val STARTING_KEY = 0
    }
}