package com.example.longdroid.presentation.article

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.data.datasource.LongBlackStorage
import com.example.longdroid.data.model.response.ResponseArticleDto
import com.example.longdroid.databinding.ItemArticleBodyBinding
import com.example.longdroid.databinding.ItemArticleSubtitleBinding
import com.example.longdroid.util.extension.setOnSingleClickListener

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
        private var binding: ItemArticleBodyBinding,
        private val addReadMark: (TextView, String, Int) -> Unit,
        private val reLoadBookMark: (TextView, String) -> Unit,
    ) :
        ArticleParagraphViewHolder(binding.root) {

        fun onBind(
            articleBody: ResponseArticleDto.Paragraph,
        ) {
            with(binding) {
                tvArticleMain.text = articleBody.content

                tvArticleMain.setOnSingleClickListener {
                    addReadMark(tvArticleMain, articleBody.content, absoluteAdapterPosition)
                }
                if (LongBlackStorage.bookMarkIdx != -1) {
                    if (absoluteAdapterPosition == LongBlackStorage.bookMarkIdx) {
                        reLoadBookMark(tvArticleMain, articleBody.content)
                    }
                }
            }
        }
    }
}
