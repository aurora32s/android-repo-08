package com.example.reposearchapp.util

sealed class Event {
    data class ShowSnackBar(val message: String): Event()
}