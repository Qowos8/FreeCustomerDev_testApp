package com.example.freecustomerdev_testapp.presentation

import com.example.freecustomerdev_testapp.data.AnnounceApi

sealed class AnnounceState {
    object Loading : AnnounceState()
    data class Success(val announce: AnnounceApi) : AnnounceState()
    data class Error(val errorMessage: String) : AnnounceState()
}
