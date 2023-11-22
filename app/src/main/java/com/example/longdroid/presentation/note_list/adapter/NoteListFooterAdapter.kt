package com.example.longdroid.presentation.note_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.R
import com.example.longdroid.presentation.note_list.viewholder.FooterViewHolder

class NoteListFooterAdapter : RecyclerView.Adapter<FooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note_list_footer, parent, false)
        return FooterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
    }

    override fun getItemCount() = 1
}
