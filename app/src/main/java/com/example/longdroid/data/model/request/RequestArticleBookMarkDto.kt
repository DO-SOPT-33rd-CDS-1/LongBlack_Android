package com.example.longdroid.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestArticleBookMarkDto(
    @SerialName("bookMarkIdx")
    val bookMarkIdx: Int,
)
