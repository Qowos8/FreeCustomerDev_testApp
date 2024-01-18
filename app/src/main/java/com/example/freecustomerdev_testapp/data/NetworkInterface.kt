package com.example.freecustomerdev_testapp.data

interface NetworkInterface {
    suspend fun getAnnounce(): AnnounceApi
}