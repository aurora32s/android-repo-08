package com.example.reposearchapp.model.issue

enum class IssueType(
    val state: String
) {
    OPEN("open"),
    CLOSED("closed"),
    UNKNOWN("unknown");

    companion object {
        fun getIssueTypeByState(state: String?): IssueType =
            values().find { it.state == state } ?: UNKNOWN
    }
}