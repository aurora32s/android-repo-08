package com.example.reposearchapp.presentation.home.issue

import com.example.reposearchapp.R
import com.example.reposearchapp.databinding.FragmentIssueBinding
import com.example.reposearchapp.presentation.base.BaseFragment

class IssueFragment : BaseFragment<FragmentIssueBinding>(R.layout.fragment_issue) {
    companion object {
        const val TAG = "IssueFragment"
        fun newInstance() = IssueFragment()
    }
}