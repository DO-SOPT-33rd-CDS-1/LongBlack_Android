package com.example.longdroid.data.service

import com.example.longdroid.data.model.response.LibraryResponse
import retrofit2.Call
import retrofit2.http.GET

interface LibraryApiService {
    @GET("/api/stamp")
    fun getStampCount(): Call<LibraryResponse>
}
