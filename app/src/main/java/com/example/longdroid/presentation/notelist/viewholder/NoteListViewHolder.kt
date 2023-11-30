package com.example.longdroid.presentation.notelist.viewholder

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.R
import com.example.longdroid.data.di.ServicePool
import com.example.longdroid.data.model.request.RequestLike
import com.example.longdroid.data.model.response.ResponseNote
import com.example.longdroid.databinding.ItemNoteBinding
import com.example.longdroid.util.extension.setOnSingleClickListener
import com.example.longdroid.util.extension.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteListViewHolder(
    private val binding: ItemNoteBinding,
    private val onItemClicked: (ResponseNote, Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private val btnLiked = binding.btnLiked
    private var currentNote: ResponseNote? = null
    private var currentPosition: Int? = null

    init {
        clickBtnLiked()
        itemView.setOnClickListener {
            currentNote?.let { note ->
                currentPosition?.let { position ->
                    onItemClicked(note, position)
                }
            }
        }
    }

    private fun clickBtnLiked() {
        btnLiked.setOnSingleClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                runCatching {
                    withContext(Dispatchers.IO) {
                        val response = ServicePool.likedService.putLiked(
                            RequestLike(
                                LIST_VIEW_CHECK,
                                currentNote?.id ?: 0,
                            ),
                        )
                        response
                    }
                }.onSuccess { response ->
                    if (response.isSuccessful) {
                        Log.d("tongsin", response.code().toString())
                        currentNote?.let { note ->
                            note.like = !note.like
                            setLikedBtn(note)
                        }
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

    fun bind(note: ResponseNote, imageResourceId: Int, position: Int) {
        currentNote = note
        currentPosition = position
        with(binding) {
            tvNoteTitle.text = note.title
            tvNoteWriter.text = note.writer
            tvNoteAlphabet.text = note.postType
            ivNoteImg.setImageResource(imageResourceId)

            setBackgroundTint(note)
            setLikedBtn(note)
        }
    }

    private fun setBackgroundTint(note: ResponseNote) {
        val colorInt = Color.parseColor(note.color)
        val colorStateList = ColorStateList.valueOf(colorInt)
        ViewCompat.setBackgroundTintList(binding.containerNoteText, colorStateList)
    }

    private fun setLikedBtn(note: ResponseNote) {
        if (note.like) {
            btnLiked.setImageResource(R.drawable.ic_book_mark_on_big)
        } else {
            btnLiked.setImageResource(R.drawable.ic_book_mark_off_big)
        }
    }

    companion object {
        const val TOAST_MESSAGE = "예기치 못한 오류로 인하여 좋아요 수정이 되지 않았어요"
        const val LIST_VIEW_CHECK = true
    }
}
