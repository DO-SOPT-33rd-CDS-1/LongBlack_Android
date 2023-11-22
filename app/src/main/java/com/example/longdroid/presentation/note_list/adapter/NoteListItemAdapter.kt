package com.example.longdroid.presentation.note_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.R
import com.example.longdroid.presentation.note_list.viewholder.NoteListViewHolder

class NoteListItemAdapter : RecyclerView.Adapter<NoteListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 3
    }
}
