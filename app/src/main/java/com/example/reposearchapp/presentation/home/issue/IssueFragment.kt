package com.example.reposearchapp.presentation.home.issue

import android.os.Bundle
import android.view.View
import com.example.reposearchapp.R
import com.example.reposearchapp.databinding.FragmentIssueBinding
import com.example.reposearchapp.model.issue.IssueType
import com.example.reposearchapp.presentation.adapter.issue.IssueListAdapter
import com.example.reposearchapp.presentation.adapter.issue.IssueOptionAdapter
import com.example.reposearchapp.presentation.base.BaseFragment

class IssueFragment : BaseFragment<FragmentIssueBinding>(R.layout.fragment_issue) {

    // TODO 의존성 주입으로 수정할 예정
    private val viewModel by lazy { IssueViewModel() }

    private lateinit var issueListAdapter: IssueListAdapter
    private lateinit var issueOptionListAdapter: IssueOptionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
        observeIssue()
    }

    private fun initViews() = with(binding) {
        if (::issueListAdapter.isInitialized.not()) {
            // TODO 이런 상황이 일어날 수 있는 경우 찾아보기
            issueListAdapter = IssueListAdapter()
            issueRecyclerView.adapter = issueListAdapter
        }
        if (::issueOptionListAdapter.isInitialized.not()) {
            issueOptionListAdapter =
                IssueOptionAdapter(requireContext(), IssueType.values().toList(), viewModel)
            spinnerIssueOption.adapter = issueOptionListAdapter
        }
    }

    private fun bindViews() = with(binding) {
        spinnerIssueOption.setOnItemClickListener { viewModel.changeIssueType(it) }
        spinnerIssueOption.setOnFocusedListener { hasFocused ->
            layoutIssueFilter.setBackgroundResource(
                if (hasFocused) R.drawable.background_issue_filter_focused
                else R.drawable.background_issue_filter_unfocused
            )
        }
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