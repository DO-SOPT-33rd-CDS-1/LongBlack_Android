package com.example.longdroid.presentation.notelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.databinding.ItemNoteListHeaderBinding
import com.example.longdroid.presentation.notelist.viewholder.HeaderViewHolder

class NoteListHeaderAdapter(private val onBackButtonClicked: () -> Unit) :
    RecyclerView.Adapter<HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val binding =
            ItemNoteListHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeaderViewHolder(binding, onBackButtonClicked)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
    }

    override fun getItemCount() = 1
}
