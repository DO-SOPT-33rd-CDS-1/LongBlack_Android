package com.example.longdroid.presentation.article

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.longdroid.R
import com.example.longdroid.data.model.response.ResponseArticleDto
import com.example.longdroid.databinding.ItemArticleTitleBinding
import com.example.longdroid.util.extension.setOnSingleClickListener

class ArticleTitleViewHolder(private val binding: ItemArticleTitleBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(articleTitle: ResponseArticleDto) {
        with(binding) {
            tvArticleTitle.text = articleTitle.title
            tvArticleAuthor.text = articleTitle.writer
            tvArticleCategory.text = articleTitle.postType
            tvArticleDate.text = articleTitle.createdDate
            if (articleTitle.stamp) {
                ivArticleCoffee.load(R.drawable.ic_coffee_sticker_on_big)
            } else {
                ivArticleCoffee.load(R.drawable.ic_coffee_sticker_off_big)
            }
        }
    }
}
