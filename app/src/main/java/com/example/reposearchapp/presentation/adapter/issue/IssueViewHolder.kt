package com.example.reposearchapp.presentation.adapter.issue

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.reposearchapp.databinding.ItemIssueBinding
import com.example.reposearchapp.model.issue.IssueModel
import com.example.reposearchapp.presentation.home.issue.IssueFragment
import com.example.reposearchapp.util.toDate

class IssueViewHolder(
    private val binding: ItemIssueBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bindView(issue: IssueModel) = with(binding) {
        tvRepoName.text = issue.repositoryTitle
        tvIssueNumber.text = "#${issue.issueNumber}"
        tvIssueTitle.text = issue.issueTitle
//        tvIssueDate.text = issue.createdAt
    }
}