package com.example.reposearchapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.reposearchapp.R
import com.example.reposearchapp.data.entity.issue.Issue
import com.example.reposearchapp.databinding.FragmentHomeBinding
import com.example.reposearchapp.model.home.TabType
import com.example.reposearchapp.presentation.adapter.home.HomeViewPagerAdapter
import com.example.reposearchapp.presentation.base.BaseFragment
import com.example.reposearchapp.presentation.home.issue.IssueFragment
import com.example.reposearchapp.presentation.home.notification.NotificationFragment
import com.example.reposearchapp.presentation.search.SearchFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    // tab 에 보여지는
    private val tabTitleList = listOf(R.string.text_tab_issue, R.string.text_tab_noti)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        initViews()
    }

    private fun initViews() {
        binding.btnSearch.setOnClickListener {
            navigateToSearchFragment()
        }
    }

    private fun bindViews() = with(binding) {
        viewPager.adapter = HomeViewPagerAdapter(
            this@HomeFragment,
            listOf(
                IssueFragment.newInstance(),
                NotificationFragment.newInstance()
            )
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = requireContext().getString(tabTitleList[position])
        }.attach()
    }

    private fun navigateToSearchFragment() {
        val f = parentFragmentManager.findFragmentByTag(SearchFragment.TAG)
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(SearchFragment.TAG)
            f?.let { replace(R.id.fragment_container_main, it, SearchFragment.TAG) }
                ?: replace(R.id.fragment_container_main, SearchFragment(), SearchFragment.TAG)
        }
    }

//    private enum class TabType(
//        @StringRes
//        val tabName: Int,
//        val fragment: Fragment
//    ) {
//        ISSUE(R.string.text_tab_issue, IssueFragment.newInstance()),
//        NOTIFICATION(R.string.text_tab_noti, NotificationFragment.newInstance())
//    }

    companion object {
        const val TAG = "HomeFragment"
    }
}