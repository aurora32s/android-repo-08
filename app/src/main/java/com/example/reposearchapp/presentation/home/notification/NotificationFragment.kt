package com.example.reposearchapp.presentation.home.notification

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.reposearchapp.R
import com.example.reposearchapp.databinding.FragmentNotificationBinding
import com.example.reposearchapp.presentation.adapter.notification.NotificationAdapter
import com.example.reposearchapp.presentation.adapter.notification.NotificationItemSwipeHelper
import com.example.reposearchapp.presentation.base.BaseFragment

class NotificationFragment :
    BaseFragment<FragmentNotificationBinding>(R.layout.fragment_notification) {

    // TODO 의존성 주입으로 수정할 예정
    private val viewModel: NotificationViewModel by viewModels()

    private lateinit var notificationAdapter: NotificationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
        observeNotification()
    }

    private fun initViews() = with(binding) {
        notificationAdapter = NotificationAdapter()
        recyclerNotification.adapter = notificationAdapter

        val notificationSwipeHelper = NotificationItemSwipeHelper(requireContext())
        val itemTouchHelper = ItemTouchHelper(notificationSwipeHelper)
        itemTouchHelper.attachToRecyclerView(recyclerNotification)
    }

    private fun bindViews() {

    }

    private fun observeNotification() =
        viewModel.notificationStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                NotificationState.UnInitialState -> viewModel.fetchData()
                NotificationState.Loading -> handleLoading()
                is NotificationState.Success -> handleSuccess(it)
                is NotificationState.Error -> handleError(it)
            }
        }

    private fun handleLoading() {

    }

    private fun handleSuccess(state: NotificationState.Success) {
        notificationAdapter.submitList(state.notifications)
    }

    private fun handleError(state: NotificationState.Error) {

    }

    companion object {
        const val TAG = "NotificationFragment"
        fun newInstance() = NotificationFragment()
    }
}