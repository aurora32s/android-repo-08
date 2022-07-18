package com.example.reposearchapp.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.reposearchapp.R
import com.example.reposearchapp.databinding.FragmentProfileBinding
import com.example.reposearchapp.presentation.base.BaseFragment

/**
 * 페어 프로그래밍
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    val viewmodel by activityViewModels<ProfileViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
    }

    // 고민 1. observe를 위치를 어디에 둘까?
    private fun observeState() {
        viewmodel.profileUiState.observe(viewLifecycleOwner) {
            when(it){
                is ProfileUiState.Loading -> {

                }
                is ProfileUiState.Success -> {
                    println(it.user)
                }
                is ProfileUiState.Error -> {
                    println(it)
                }
            }
        }
    }

    companion object {
        const val TAG = "ProfileFragment"
    }
}