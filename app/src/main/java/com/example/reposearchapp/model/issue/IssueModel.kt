package com.example.reposearchapp.model.issue

data class IssueModel(
    val id: Int,
    val createdAt: String,
    val repositoryTitle: String,
    val issueNumber: Int,
    val issueTitle: String,
    val state: IssueType
)
