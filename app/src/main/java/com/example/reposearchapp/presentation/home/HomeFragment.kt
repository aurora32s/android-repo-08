package com.example.reposearchapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.reposearchapp.R
import com.example.reposearchapp.databinding.FragmentHomeBinding
import com.example.reposearchapp.model.home.TabType
import com.example.reposearchapp.presentation.base.BaseFragment
import com.example.reposearchapp.presentation.home.issue.IssueFragment
import com.example.reposearchapp.presentation.home.notification.NotificationFragment
import com.google.android.material.tabs.TabLayout

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        initViews()
    }

    private fun initViews() {
        showFragment(IssueFragment.newInstance(), IssueFragment.TAG)
    }

    private fun bindViews() = with(binding) {
        // TODO tab item 도 동적으로 추가할 수 있도록 코드 수정
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (TabType.getTabTypeByPosition(tab.position)) {
                    TabType.ISSUE -> showFragment(IssueFragment.newInstance(), IssueFragment.TAG)
                    TabType.NOTIFICATION -> showFragment(
                        NotificationFragment.newInstance(),
                        NotificationFragment.TAG
                    )
                    else -> {}
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = childFragmentManager.findFragmentByTag(tag)

        // 다른 fragment 들은 Hide
        childFragmentManager.fragments.forEach {
            childFragmentManager.beginTransaction().hide(it).commitAllowingStateLoss()
        }

        // fragment show
        findFragment?.let {
            childFragmentManager.commit(true) {
                setReorderingAllowed(true)
                show(it)
            }
        } ?: kotlin.run {
            childFragmentManager.commit(allowStateLoss = true) {
                setReorderingAllowed(true)
                add(R.id.fragmentContainer, fragment, tag)
            }
        }
    }
}