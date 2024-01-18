package com.example.freecustomerdev_testapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freecustomerdev_testapp.data.KtorClient
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _announceState = MutableLiveData<AnnounceState>()
    val announceState: LiveData<AnnounceState> get() = _announceState
    private val client = KtorClient()

    fun loadData() {
        viewModelScope.launch {
            try {
                _announceState.value = AnnounceState.Loading
                val response = client.getAnnounce()
                Log.d("response", "$response")
                _announceState.value = AnnounceState.Success(response)
            } catch (e: Exception) {
                _announceState.value = AnnounceState.Error(e.message ?: "Unknown error")
            }
        }
    }
}