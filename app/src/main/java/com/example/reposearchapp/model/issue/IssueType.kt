package com.example.reposearchapp.model.issue

import com.example.reposearchapp.R

enum class IssueType(
    val state: String,
    val option: String,
    val iconResId: Int
) {
    OPEN("open", "Open", R.drawable.ic_issue_open),
    CLOSED("closed", "Closed", R.drawable.ic_issue_closed),
    ALL("all", "All", R.drawable.ic_issue_open);

    companion object {
        fun getIssueTypeByState(state: String?): IssueType =
            values().find { it.state == state } ?: ALL

        fun getIssueTypeByOrdinary(ordinary: Long): IssueType =
            values().find { it.ordinal == ordinary.toInt() } ?: ALL
    }
}