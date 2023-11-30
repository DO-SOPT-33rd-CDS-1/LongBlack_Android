package com.example.longdroid.presentation.notelist.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.databinding.ItemNoteListHeaderBinding
import com.example.longdroid.util.extension.setOnSingleClickListener

class HeaderViewHolder(binding: ItemNoteListHeaderBinding, onBackButtonClicked: () -> Unit) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.btnNoteListBack.setOnSingleClickListener {
            onBackButtonClicked()
        }
    }
}