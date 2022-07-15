package com.example.reposearchapp.presentation.home.issue

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reposearchapp.R
import com.example.reposearchapp.data.repository.issue.DefaultIssueRepository
import com.example.reposearchapp.data.repository.issue.IssueRepository
import com.example.reposearchapp.model.issue.IssueType
import kotlinx.coroutines.launch
import java.lang.Exception

class IssueViewModel(
    private val issueRepository: IssueRepository = DefaultIssueRepository()
) : ViewModel() {

    private val _issueStateLiveData = MutableLiveData<IssueState>(IssueState.UnInitialState)

    // TODO liveData로 데이터 타입을 지정해주어야 LiveData 로 넣을 수 있습니다.
    val issueStateLiveData: LiveData<IssueState>
        get() = _issueStateLiveData

    private var _issueType = IssueType.OPEN
    val issueType
        get() = _issueType

    fun fetchData() = viewModelScope.launch {
        try {
            _issueStateLiveData.value = IssueState.Loading
            val issues = issueRepository.getIssues(issueType.state)

            if (issues.isNotEmpty()) {
                _issueStateLiveData.value = IssueState.Success(issues.map { it.toModel() })
            }
        } catch (exception: Exception) {
            _issueStateLiveData.value = IssueState.Error(R.string.error_issue_list)
        }
    }

    /**
     * issue filtering option 변경
     */
    fun changeIssueType(issueTypeId: Long) {
        _issueType = IssueType.getIssueTypeByOrdinary(issueTypeId)
        fetchData()
    }
}