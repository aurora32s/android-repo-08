package com.example.reposearchapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.reposearchapp.data.RepoItemModel
import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.remote.GithubApi
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.util.GithubLanguageColorUtil
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val color: GithubLanguageColorUtil,
    private val githubApi: GithubApi
) {

    private val _repoList = MutableLiveData<List<RepoItemModel>>()
    val repoList: LiveData<List<RepoItemModel>> get() = _repoList

    suspend fun parsingColor() {
        color.parseJson()
    }

    suspend fun search(query: String) {
        if (query.isBlank()) {
            _repoList.value = listOf()
            return
        }

        delay(DEBOUNCE_TIME)

        when (val result = safeApiCall { githubApi.getRepos(query) }) {
            is Result.Success -> {
                _repoList.value =
                    result.data.items.map { it.toModel(color.colorMap?.get(it.language)) }
            }
            is Result.Error -> {
                _repoList.value = listOf()
            }
        }
    }

    companion object {
        const val DEBOUNCE_TIME = 300L
    }
}