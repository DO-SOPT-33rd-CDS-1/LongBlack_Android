package com.example.longdroid.data.service

import com.example.longdroid.data.model.request.RequestArticleBookMarkDto
import com.example.longdroid.data.model.request.RequestArticleLikeDto
import com.example.longdroid.data.model.request.RequestArticleStampDto
import com.example.longdroid.data.model.response.ResponseArticleDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ArticleService {

    @PUT("api/likey")
    suspend fun putLikeInfo(
        @Body request: RequestArticleLikeDto,
    )

    @GET("api/post/{postId}")
    suspend fun getArticleInfo(@Path("postId") postId: Int): ResponseArticleDto

    @POST("/api/stamp")
    suspend fun postStampInfo(@Body request: RequestArticleStampDto)

    @POST("/api/bookmark/{postId}")
    suspend fun postBookMarkInfo(
        @Path("postId") postId: Int,
        @Body request: RequestArticleBookMarkDto,
    )

    @DELETE("/api/bookmark/{postId}")
    suspend fun deleteBookMarkInfo(
        @Path("postId") postId: Int,
    )
}
