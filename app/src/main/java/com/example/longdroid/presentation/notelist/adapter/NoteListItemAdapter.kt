package com.example.longdroid.presentation.notelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.longdroid.data.model.response.ResponseNote
import com.example.longdroid.databinding.ItemNoteBinding
import com.example.longdroid.presentation.notelist.viewholder.NoteListViewHolder

class NoteListItemAdapter : ListAdapter<ResponseNote, NoteListViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
        return NoteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<ResponseNote>() {
    override fun areItemsTheSame(oldItem: ResponseNote, newItem: ResponseNote): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResponseNote, newItem: ResponseNote): Boolean {
        return oldItem == newItem
    }
}
