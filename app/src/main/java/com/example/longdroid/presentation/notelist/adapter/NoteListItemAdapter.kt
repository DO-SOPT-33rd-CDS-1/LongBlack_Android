package com.example.longdroid.presentation.notelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.longdroid.R
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
        var resourceId: Int = when (position) {
            0 -> R.drawable.img_note_open_gallery
            1 -> R.drawable.img_note_books
            2 -> R.drawable.img_note_bottles
            3 -> R.drawable.img_note_structure
            4 -> R.drawable.img_note_kolon
            5 -> R.drawable.img_note_girl
            6 -> R.drawable.img_note_barista
            else -> R.drawable.img_note_sushi
        }
        val note = getItem(position)
        holder.bind(note, resourceId)
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
