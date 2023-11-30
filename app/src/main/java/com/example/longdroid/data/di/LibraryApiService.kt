package com.example.longdroid.data.di

import retrofit2.Call
import retrofit2.http.GET

interface LibraryApiService {
    @GET("/api/stamp")
    fun getStampCount(): Call<LibraryResponse>
}
