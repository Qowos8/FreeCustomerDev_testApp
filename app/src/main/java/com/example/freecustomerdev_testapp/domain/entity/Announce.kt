package com.example.freecustomerdev_testapp.domain.entity

data class Announce(
    val retCode: Int,
    val result: AnnounceResult
)
data class AnnounceResult(
    val list: List<Content>
)
data class Content(
    val title: String,
    val description: String
)