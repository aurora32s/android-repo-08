package com.example.reposearchapp.presentation.home.issue

import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reposearchapp.R
import com.example.reposearchapp.databinding.FragmentIssueBinding
import com.example.reposearchapp.presentation.adapter.issue.IssueListAdapter
import com.example.reposearchapp.presentation.base.BaseFragment

class IssueFragment : BaseFragment<FragmentIssueBinding>(R.layout.fragment_issue) {

    // TODO 의존성 주입으로 수정할 예정
    private val viewModel by lazy { IssueViewModel() }

    private lateinit var issueListAdapter: IssueListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
        observeIssue()
    }

    private fun initViews() = with(binding) {
        if (::issueListAdapter.isInitialized.not()) {
            issueListAdapter = IssueListAdapter()
            issueRecyclerView.adapter = issueListAdapter
        }
    }

    private fun bindViews() {

    }

    private fun observeIssue() = viewModel.issueStateLiveData.observe(viewLifecycleOwner) {
        when (it) {
            IssueState.UnInitialState -> viewModel.fetchData()
            IssueState.Loading -> handleLoading()
            is IssueState.Success -> handleSuccess(it)
            is IssueState.Error -> handleError(it)
        }
    }

    private fun handleLoading() {

    }

    private fun handleSuccess(state: IssueState.Success) {
        issueListAdapter.submitList(state.issues)
    }

    private fun handleError(state: IssueState.Error) {
    }

    companion object {
        const val TAG = "IssueFragment"
        fun newInstance() = IssueFragment()
    }
}