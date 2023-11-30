package com.example.longdroid.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseNoteList(
    @SerialName("posts")
    val posts: List<ResponseNote>,
)

@Serializable
data class ResponseNote(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("writer")
    val writer: String,
    @SerialName("postType")
    val postType: String,
    @SerialName("color")
    val color: String,
    @SerialName("like")
    var like: Boolean,
)
