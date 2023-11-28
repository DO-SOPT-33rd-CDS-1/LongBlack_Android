package com.example.longdroid.presentation.note_list.viewholder

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.data.di.HomeServicePool
import com.example.longdroid.data.model.request.RequestLike
import com.example.longdroid.databinding.ItemNoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
    private val btnLiked = binding.btnLiked

    init {
        clickBtnLiked() // 초기화 블록에서 함수 호출
    }

    fun clickBtnLiked() {
        btnLiked.setOnClickListener {
            Toast.makeText(btnLiked.context, "Button clicked!", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = HomeServicePool.likedService.putLiked(RequestLike(true, 2))
                    if (response.isSuccessful) {
                        val responseBody = response.body().toString()
                        Log.d("tongsin", responseBody)
                        Log.d("tongsin", response.code().toString())
                    } else {
                        Log.d("tongsin", "error")
                    }
                } catch (e: Exception) {
                    // 예외 처리
                    Log.d("tongsin", e.toString())
                }
            }
        }
    }
}
