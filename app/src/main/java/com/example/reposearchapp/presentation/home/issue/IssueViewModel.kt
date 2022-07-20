package com.example.reposearchapp.presentation.home.issue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.reposearchapp.R
import com.example.reposearchapp.data.entity.issue.Issue
import com.example.reposearchapp.data.repository.issue.IssueRepository
import com.example.reposearchapp.model.issue.IssueModel
import com.example.reposearchapp.model.issue.IssueType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception

class IssueViewModel(
    private val issueRepository: IssueRepository = IssueRepository()
) : ViewModel() {

    private val _issueStateLiveData = MutableLiveData<IssueState>(IssueState.UnInitialState)

    // TODO liveData로 데이터 타입을 지정해주어야 LiveData 로 넣을 수 있습니다.
    val issueStateLiveData: LiveData<IssueState>
        get() = _issueStateLiveData

    var issueType = MutableStateFlow(IssueType.OPEN)
        private set

    fun getIssues(): Flow<PagingData<IssueModel>> {
        return issueRepository.getIssuesByPaging(REQUEST_ISSUE_TYPE.state).cachedIn(viewModelScope)
            .map { issues -> issues.map { issue -> issue.toModel() } }
            .combine(issueType) { issues, query ->
                if (query == IssueType.ALL) issues else issues.filter { it.state == query }
            }
            .onStart { _issueStateLiveData.value = IssueState.Loading }
            .onCompletion { _issueStateLiveData.value = IssueState.FetchFinish }
            .catch { _issueStateLiveData.value = IssueState.Error(R.string.error_issue_list) }
    }

    /**
     * issue filtering option 변경
     */
    fun changeIssueType(issueTypeId: Long) = viewModelScope.launch {
        issueType.emit(IssueType.getIssueTypeByOrdinary(issueTypeId))
    }

    companion object {
        private val REQUEST_ISSUE_TYPE = IssueType.ALL
    }
}