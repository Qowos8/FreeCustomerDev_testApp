package com.example.freecustomerdev_testapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnounceApi(
    @SerialName("retCode")
    val retCode: Int,
    @SerialName("result")
    val result: AnnounceResultApi
)
@Serializable
data class AnnounceResultApi(
    @SerialName("list")
    val list: List<ContentApi>
)
@Serializable
data class ContentApi(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String
)
