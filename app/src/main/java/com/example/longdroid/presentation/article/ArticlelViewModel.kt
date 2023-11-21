package com.example.longdroid.presentation.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArticlelViewModel() : ViewModel() {

    private val _isBookMarked = MutableLiveData(false)
    val isBookMarked: LiveData<Boolean> get() = _isBookMarked

    fun setBookMarkState() {
        _isBookMarked.value = _isBookMarked.value?.not()
    }
}
