package com.example.reposearchapp.presentation.home.issue

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reposearchapp.data.repository.issue.DefaultIssueRepository
import com.example.reposearchapp.data.repository.issue.IssueRepository
import com.example.reposearchapp.model.issue.IssueType
import kotlinx.coroutines.launch
import java.lang.Exception

class IssueViewModel(
    private val issueRepository: IssueRepository = DefaultIssueRepository()
) : ViewModel() {

    private val _issueStateLiveData = MutableLiveData<IssueState>(IssueState.UnInitialState)
    val issueStateLiveData
        get() = _issueStateLiveData

    private var issueType = IssueType.CLOSED

    fun fetchData() = viewModelScope.launch {
        try {
            _issueStateLiveData.value = IssueState.Loading
            val issues = issueRepository.getIssues(issueType.state)

            if (issues.isNotEmpty()) {
                _issueStateLiveData.value = IssueState.Success(issues.map { it.toModel() })
            }
        } catch (exception: Exception) {
        }
    }
}