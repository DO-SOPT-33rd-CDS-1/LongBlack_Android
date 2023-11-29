package com.example.longdroid.presentation.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.longdroid.data.di.ServicePool.articleService
import com.example.longdroid.data.model.request.RequestArticleBookMarkDto
import com.example.longdroid.data.model.request.RequestArticleLikeDto
import com.example.longdroid.data.model.request.RequestArticleStampDto
import com.example.longdroid.data.model.response.ResponseArticleDto
import kotlinx.coroutines.launch

class ArticlelViewModel() : ViewModel() {

    private val _articleData = MutableLiveData<ResponseArticleDto>()
    val articleData: LiveData<ResponseArticleDto> get() = _articleData

    fun postStampState(postId: Long) {
        viewModelScope.launch {
            runCatching {
                articleService.postStampInfo(
                    RequestArticleStampDto(
                        postId,
                    ),
                )
            }
        }
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
                _articleData.value = it
            }.onFailure {
                // TODO: 에러 처리
            }
        }
    }

    fun postBookMarkState(postId: Int, bookMarkIdx: Int) {
        viewModelScope.launch {
            runCatching {
                articleService.postBookMarkInfo(
                    postId,
                    RequestArticleBookMarkDto(
                        bookMarkIdx,
                    ),
                )
            }
        }
    }

    fun deleteBookMarkState(postId: Int) {
        viewModelScope.launch {
            runCatching {
                articleService.deleteBookMarkInfo(postId)
            }
        }
    }
}
