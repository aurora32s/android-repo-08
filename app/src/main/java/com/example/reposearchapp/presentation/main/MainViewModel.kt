package com.example.reposearchapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reposearchapp.data.repository.AccessTokenRepository
import com.example.reposearchapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val accessTokenRepository: AccessTokenRepository) :
    ViewModel() {
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    val accessToken = accessTokenRepository.token

    fun requestAccessToken(code: String) {
        viewModelScope.launch {
            _event.emit(accessTokenRepository.getToken(code))
        }
    }
}