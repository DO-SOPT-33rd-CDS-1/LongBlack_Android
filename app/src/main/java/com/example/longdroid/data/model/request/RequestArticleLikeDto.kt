package com.example.longdroid.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestArticleLikeDto(
    @SerialName("postId")
    val postId: Int,
    @SerialName("isListView")
    val isListView: Boolean = false,
)
