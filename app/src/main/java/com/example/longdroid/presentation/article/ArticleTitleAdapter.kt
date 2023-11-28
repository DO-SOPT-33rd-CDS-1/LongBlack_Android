package com.example.longdroid.presentation.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.longdroid.data.model.response.ResponseArticleDto
import com.example.longdroid.databinding.ItemArticleTitleBinding
import com.example.longdroid.util.extension.ItemDiffCallback

class ArticleTitleAdapter() :
    ListAdapter<ResponseArticleDto, ArticleTitleViewHolder>(
        ItemDiffCallback<ResponseArticleDto>(
            onItemsTheSame = { old, new -> old.title == new.title },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleTitleViewHolder {
        val binding =
            ItemArticleTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleTitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleTitleViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount() = currentList.size
}
