package com.example.longdroid.data.service

import com.example.longdroid.data.model.request.RequestLike
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface LikedService {
    @PUT("api/likey")
    suspend fun putLiked(
        @Body request: RequestLike,
    ): Response<Unit>
}
