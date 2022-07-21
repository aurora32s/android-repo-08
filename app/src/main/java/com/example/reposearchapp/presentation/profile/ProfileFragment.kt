package com.example.reposearchapp.presentation.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.reposearchapp.R
import com.example.reposearchapp.databinding.FragmentProfileBinding
import com.example.reposearchapp.presentation.base.BaseFragment
import com.example.reposearchapp.util.formatNumber

/**
 * 페어 프로그래밍
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val profileViewModel by activityViewModels<ProfileViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeState()
    }

    private fun initView() = with(binding) {
        ivBackProfile.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun observeState() {
        profileViewModel.profileUiState.observe(viewLifecycleOwner) {
            when (it) {
                is ProfileUiState.Loading -> {

                }
                is ProfileUiState.Success -> bindViews(it)
                is ProfileUiState.Error -> {
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindViews(state: ProfileUiState.Success) = with(binding) {
        val user = state.user
        tvFollower.text = "${user.followers.formatNumber()} Followers"
        tvFollowing.text = "${user.following.formatNumber()} Followings"
        profile = user
    }

    companion object {
        const val TAG = "ProfileFragment"
    }
}