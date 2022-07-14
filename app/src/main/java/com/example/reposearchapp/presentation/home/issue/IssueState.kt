package com.example.reposearchapp.presentation.home.issue

import com.example.reposearchapp.model.issue.IssueModel

sealed interface IssueState {
    object UnInitialState : IssueState
    object Loading : IssueState

    data class Success(
        val issues: List<IssueModel>
    ) : IssueState

    data class Error(
        val errorMsg: Int
    ) : IssueState
}