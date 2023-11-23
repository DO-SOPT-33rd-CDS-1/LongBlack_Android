package com.example.longdroid.presentation.note_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.R
import com.example.longdroid.presentation.note_list.viewholder.HeaderViewHolder

class NoteListHeaderAdapter : RecyclerView.Adapter<HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note_list_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
    }

    override fun getItemCount() = 1
}
