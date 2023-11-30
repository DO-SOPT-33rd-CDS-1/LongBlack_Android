package com.example.longdroid.data.service

import com.example.longdroid.data.model.response.ResponseNoteList
import retrofit2.Response
import retrofit2.http.GET

interface NoteListService {
    @GET("api/post")
    suspend fun getPost(): Response<ResponseNoteList>
}
