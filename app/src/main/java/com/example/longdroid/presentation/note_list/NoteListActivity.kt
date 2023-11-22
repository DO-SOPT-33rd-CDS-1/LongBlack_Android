package com.example.longdroid.presentation.note_list

import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.longdroid.R
import com.example.longdroid.databinding.ActivityNoteListBinding
import com.example.longdroid.presentation.note_list.adapter.NoteListFooterAdapter
import com.example.longdroid.presentation.note_list.adapter.NoteListHeaderAdapter
import com.example.longdroid.presentation.note_list.adapter.NoteListItemAdapter
import com.example.longdroid.util.binding.BindingActivity

class NoteListActivity : BindingActivity<ActivityNoteListBinding>(R.layout.activity_note_list) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val headerAdapter = NoteListHeaderAdapter()
        val itemAdapter = NoteListItemAdapter()
        val footerAdapter = NoteListFooterAdapter()

        val concatAdapter = ConcatAdapter(headerAdapter, itemAdapter, footerAdapter)

        binding.rvNoteList.adapter = concatAdapter
        binding.rvNoteList.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        binding.rvNoteList.adapter = null
        super.onDestroy()
    }
}
