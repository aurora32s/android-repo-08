package com.example.reposearchapp.model.issue

import com.example.reposearchapp.R

enum class IssueType(
    val state: String,
    val iconResId: Int
) {
    OPEN("open", R.drawable.ic_issue_open),
    CLOSED("closed", R.drawable.ic_issue_closed),
    UNKNOWN("unknown", R.drawable.ic_issue_open);

    companion object {
        fun getIssueTypeByState(state: String?): IssueType =
            values().find { it.state == state } ?: UNKNOWN
    }
}