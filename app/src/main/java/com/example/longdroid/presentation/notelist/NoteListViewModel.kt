package com.example.longdroid.presentation.notelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.longdroid.data.model.response.ResponseNoteList
import com.example.longdroid.data.service.NoteListService
import retrofit2.Response

class NoteListViewModel(private val noteService: NoteListService) : ViewModel() {
    val noteListLiveData: MutableLiveData<Response<ResponseNoteList>> = MutableLiveData()
}
