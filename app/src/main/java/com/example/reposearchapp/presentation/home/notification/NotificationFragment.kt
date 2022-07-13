package com.example.reposearchapp.presentation.home.notification

import com.example.reposearchapp.R
import com.example.reposearchapp.databinding.FragmentNotificationBinding
import com.example.reposearchapp.presentation.base.BaseFragment

class NotificationFragment :
    BaseFragment<FragmentNotificationBinding>(R.layout.fragment_notification) {

    companion object {
        const val TAG = "NotificationFragment"
        fun newInstance() = NotificationFragment()
    }
}