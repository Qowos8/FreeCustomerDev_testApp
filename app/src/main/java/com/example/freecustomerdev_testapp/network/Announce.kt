package com.example.freecustomerdev_testapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Announce(
    @SerialName("retCode")
    val retCode: Int,
    @SerialName("result")
    val result: AnnounceResult
)
@Serializable
data class AnnounceResult(

    @SerialName("list")
    val list: List<Content>
)
@Serializable
data class Content(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String
)
