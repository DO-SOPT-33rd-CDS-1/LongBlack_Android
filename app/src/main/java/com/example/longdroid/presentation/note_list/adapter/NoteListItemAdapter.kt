package com.example.longdroid.presentation.note_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.databinding.ItemNoteBinding
import com.example.longdroid.presentation.note_list.viewholder.NoteListViewHolder

class NoteListItemAdapter() :
    RecyclerView.Adapter<NoteListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.clickBtnLiked()
    }

    override fun getItemCount(): Int {
        return 3
    }
}
