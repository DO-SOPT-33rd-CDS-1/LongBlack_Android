package com.example.longdroid.presentation.article

import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.longdroid.R
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
    ) :
        ArticleParagraphViewHolder(binding.root) {

        fun onBind(
            articleBody: ResponseArticleDto.Paragraph,
        ) {
            with(binding) {
                tvArticleMain.text = articleBody.content

                tvArticleMain.setOnSingleClickListener {
                    val clickedText = binding.tvArticleMain.text.toString()
                    addReadMark(binding.tvArticleMain, clickedText, absoluteAdapterPosition)
                }
                if (LongBlackStorage.bookMarkIdx != -1) {
                    if (absoluteAdapterPosition == LongBlackStorage.bookMarkIdx) {
                        val clickedText = tvArticleMain.text.toString()
                        addReadMark(binding.tvArticleMain, clickedText, absoluteAdapterPosition)
                    }
                }
            }
        }

    }
}
