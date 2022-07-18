package com.example.reposearchapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reposearchapp.BuildConfig
import com.example.reposearchapp.data.repository.AccessTokenRepository
import com.example.reposearchapp.data.entity.AccessTokenRequest
import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.remote.AccessService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val accessTokenRepository: AccessTokenRepository) :
    ViewModel() {
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> get() = _event

    val accessToken = accessTokenRepository.token

    fun requestAccessToken(code: String) {
        viewModelScope.launch {
            when (val result = safeApiCall {
                AccessService.api.requestAccessToken(
                    AccessTokenRequest(
                        clientId = BuildConfig.GITHUB_CLIENT_ID,
                        clientSecret = BuildConfig.GITHUB_CLIENT_SECRET,
                        code = code
                    )
                )
            }) {
                is Result.Success -> {
                    accessTokenRepository.saveToken(result.data.accessToken)
                }
                is Result.Error -> {
                    _event.emit(Event.ShowSnackBar(result.exception))
                }
            }
        }
    }
}