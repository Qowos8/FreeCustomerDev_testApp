package com.example.freecustomerdev_testapp.domain

import com.example.freecustomerdev_testapp.domain.entity.Content

interface AnnounceInterface {
    suspend fun getAnnounce(): List<Content>

}