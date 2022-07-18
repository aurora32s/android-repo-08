package com.example.reposearchapp.data.repository.issue

import com.example.reposearchapp.data.entity.issue.Issue

interface IssueRepository {
    // 특정 사용자의 모든 이슈 정보 요청
    suspend fun getIssues(state: String): List<Issue>
}