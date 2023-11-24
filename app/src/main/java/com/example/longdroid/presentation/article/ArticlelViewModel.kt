package com.example.longdroid.presentation.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArticlelViewModel() : ViewModel() {

    private val _isBookMarked = MutableLiveData(false)
    val isBookMarked: LiveData<Boolean> get() = _isBookMarked

    private val _isStamp = MutableLiveData(false)
    val isStamp: LiveData<Boolean> get() = _isStamp

    private val _isLike = MutableLiveData(false)
    val isLike: LiveData<Boolean> get() = _isLike

    fun setLikeState() {
        _isLike.value = _isLike.value?.not()
    }

    fun setStampState() {
        _isStamp.value = _isStamp.value?.not()
    }

    fun setBookMarkState() {
        _isBookMarked.value = _isBookMarked.value?.not()
    }
}
