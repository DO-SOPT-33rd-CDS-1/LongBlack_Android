package com.example.longdroid.presentation.notelist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.longdroid.R
import com.example.longdroid.databinding.ActivityNoteListBinding
import com.example.longdroid.di.ServicePool
import com.example.longdroid.presentation.article.ArticleActivity
import com.example.longdroid.presentation.notelist.adapter.NoteListFooterAdapter
import com.example.longdroid.presentation.notelist.adapter.NoteListHeaderAdapter
import com.example.longdroid.presentation.notelist.adapter.NoteListItemAdapter
import com.example.longdroid.util.binding.BindingActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteListActivity : BindingActivity<ActivityNoteListBinding>(R.layout.activity_note_list) {

    private val itemAdapter = NoteListItemAdapter { note, position ->
        if (position == 0) {
            val intent = Intent(this, ArticleActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()

        fetchNoteList()
    }

    private fun initRecyclerView() {
        val headerAdapter = NoteListHeaderAdapter {
            finish()
        }
        val footerAdapter = NoteListFooterAdapter()

        val concatAdapter = ConcatAdapter(headerAdapter, itemAdapter, footerAdapter)

        binding.rvNoteList.adapter = concatAdapter
        binding.rvNoteList.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchNoteList() {
        CoroutineScope(Dispatchers.Main).launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    val response = ServicePool.noteListService.getPost()
                    response
                }
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    response.body()?.let { noteListResponse ->
                        itemAdapter.submitList(noteListResponse.posts)
                    }
                } else {
                    Log.d("tongsin", response.errorBody().toString())
                }
            }.onFailure { e ->
                Log.d("tongsin", e.toString())
            }
        }
    }

    override fun onDestroy() {
        binding.rvNoteList.adapter = null
        super.onDestroy()
    }
}
