package com.example.longdroid.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLike(
    @SerialName("isListView")
    val isListView: Boolean,
    @SerialName("postId")
    val postId: Int,
)
