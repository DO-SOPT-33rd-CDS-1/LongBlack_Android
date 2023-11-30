package com.example.longdroid.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseArticleDto(
    @SerialName("bookmarkIdx")
    val bookmarkIdx: Int,
    @SerialName("createdDate")
    val createdDate: String,
    @SerialName("like")
    val like: Boolean,
    @SerialName("paragraphs")
    val paragraphs: List<Paragraph>,
    @SerialName("postId")
    val postId: Int,
    @SerialName("postType")
    val postType: String,
    @SerialName("stamp")
    val stamp: Boolean,
    @SerialName("title")
    val title: String,
    @SerialName("writer")
    val writer: String,
) {
    @Serializable
    data class Paragraph(
        @SerialName("content")
        val content: String,
        @SerialName("paragraphType")
        val paragraphType: String,
    )
}
