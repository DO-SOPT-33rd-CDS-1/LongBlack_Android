package com.example.longdroid.presentation.notelist.viewholder

import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.data.di.HomeServicePool
import com.example.longdroid.data.model.request.RequestLike
import com.example.longdroid.data.model.response.ResponseNote
import com.example.longdroid.databinding.ItemNoteBinding
import com.example.longdroid.util.extension.setOnSingleClickListener
import com.example.longdroid.util.extension.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteListViewHolder(private val binding: ItemNoteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val btnLiked = binding.btnLiked

    init {
        clickBtnLiked()
    }

    private fun clickBtnLiked() {
        btnLiked.setOnSingleClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                runCatching {
                    withContext(Dispatchers.IO) {
                        val response = HomeServicePool.likedService.putLiked(
                            RequestLike(
                                LIST_VIEW_CHECK,
                                2,
                            ),
                        )
                        response
                    }
                }.onSuccess { response ->
                    if (response.isSuccessful) {
                        val responseBody = response.body().toString()
                        Log.d("tongsin", response.code().toString())
                    } else {
                        Log.d("tongsin", "error")
                        btnLiked.context.showToast(
                            TOAST_MESSAGE,
                        )
                    }
                }.onFailure { e ->
                    Log.d("tongsin", e.toString())
                    btnLiked.context.showToast(
                        TOAST_MESSAGE,
                    )
                }
            }
        }
    }

    fun bind(note: ResponseNote) {
        with(binding) {
            tvNoteTitle.text = note.title
            tvNoteWriter.text = note.writer
            tvNoteAlphabet.text = note.postType
            containerNoteText.setBackgroundColor(Color.parseColor(note.color))
        }
    }

    companion object {
        const val TOAST_MESSAGE = "예기치 못한 오류로 인하여 좋아요 수정이 되지 않았어요"
        const val LIST_VIEW_CHECK = true
    }
}
