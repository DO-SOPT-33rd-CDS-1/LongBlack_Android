package com.example.longdroid.data.di

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface LibraryApiService {
    @GET("/api/stamp")
    fun getStampCount(
        @Header("Content-Type") contentType: String,
    ): Call<LibraryResponse>
}
