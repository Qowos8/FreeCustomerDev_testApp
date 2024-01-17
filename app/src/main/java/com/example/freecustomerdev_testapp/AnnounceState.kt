package com.example.freecustomerdev_testapp

import com.example.freecustomerdev_testapp.network.Announce

sealed class AnnounceState {
    object Loading : AnnounceState()
    data class Success(val announce: Announce) : AnnounceState()
    data class Error(val errorMessage: String) : AnnounceState()
}
