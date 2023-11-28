package com.example.longdroid.presentation.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.longdroid.data.di.ServicePool.articleService
import com.example.longdroid.data.model.request.RequestArticleLikeDto
import com.example.longdroid.data.model.response.ResponseArticleDto
import kotlinx.coroutines.launch

class ArticlelViewModel() : ViewModel() {

    private val _isBookMarked = MutableLiveData(false)
    val isBookMarked: LiveData<Boolean> get() = _isBookMarked

    private val _isStamp = MutableLiveData(false)
    val isStamp: LiveData<Boolean> get() = _isStamp

    private val _isLike = MutableLiveData(false)
    val isLike: LiveData<Boolean> get() = _isLike

    private val _articleTitleData = MutableLiveData<ResponseArticleDto>()
    val articleTitleData: LiveData<ResponseArticleDto> get() = _articleTitleData

    private val _articleMainData = MutableLiveData<List<ResponseArticleDto.Paragraph>>()
    val articleData: LiveData<List<ResponseArticleDto.Paragraph>> get() = _articleMainData

    fun setLikeState() {
        _isLike.value = _isLike.value?.not()
    }

    fun setStampState() {
        _isStamp.value = _isStamp.value?.not()
    }

    fun setBookMarkState() {
        _isBookMarked.value = _isBookMarked.value?.not()
    }

    fun putLikeState(postId: Int, isListView: Boolean) {
        viewModelScope.launch {
            runCatching {
                articleService.putLikeInfo(
                    RequestArticleLikeDto(
                        postId,
                        isListView,
                    ),
                )
            }
        }
    }

    fun getArticleData(postId: Int) {
        viewModelScope.launch {
            runCatching {
                articleService.getArticleInfo(postId)
            }.onSuccess {
                _articleTitleData.value = it
                _articleMainData.value = it.paragraphs
            }.onFailure {
                // TODO: 에러 처리
            }
        }
    }
}
