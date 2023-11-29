package com.example.longdroid.data.di

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LibraryResponse(
    @SerialName("stampCount")
    val stampCount: Int,
)
