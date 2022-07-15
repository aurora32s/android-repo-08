package com.example.reposearchapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reposearchapp.data.RepoItemModel
import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.entity.toModel
import com.example.reposearchapp.data.remote.GithubService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.util.GithubLanguageColorUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val color: GithubLanguageColorUtil) :
    ViewModel() {
    private var searchJob: Job? = null
    var lastQuery = ""

    private val _repoList = MutableLiveData<List<RepoItemModel>>()
    val repoList: LiveData<List<RepoItemModel>> get() = _repoList

    init {
        viewModelScope.launch {
            color.parseJson()
        }
    }

    fun search(query: String) {
        searchJob?.cancel()

        if (query.isBlank()) {
            _repoList.value = listOf()

            return
        }

        lastQuery = query
        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_TIME)
            when (val result = safeApiCall { GithubService.api.getRepos(query) }) {
                is Result.Success -> {
                    _repoList.value =
                        result.data.items.map { it.toModel(color.colorMap?.get(it.language)) }
                }
                is Result.Error -> {
                }
            }
        }
    }

    companion object {
        const val DEBOUNCE_TIME = 300L
    }
}