package com.example.longdroid.presentation.article

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.data.model.response.ResponseArticleDto
import com.example.longdroid.databinding.ItemArticleBodyBinding
import com.example.longdroid.databinding.ItemArticleSubtitleBinding

sealed class ArticleParagraphViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class SubTitleArticleViewHolder(private val binding: ItemArticleSubtitleBinding) :
        ArticleParagraphViewHolder(binding.root) {
        fun onBind(articleTitle: ResponseArticleDto.Paragraph) {
            with(binding) {
                tvArticleSubtitle.text = articleTitle.content
            }
        }
    }

    class BodyArticleViewHolder(
        private val binding: ItemArticleBodyBinding,
    ) :
        ArticleParagraphViewHolder(binding.root) {
        fun onBind(articleBody: ResponseArticleDto.Paragraph) {
            with(binding) {
                tvArticleMain.text = articleBody.content
            }
        }
    }
}
