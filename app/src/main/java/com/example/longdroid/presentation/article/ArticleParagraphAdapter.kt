package com.example.longdroid.presentation.article

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import com.example.longdroid.data.model.response.ResponseArticleDto
import com.example.longdroid.databinding.ItemArticleBodyBinding
import com.example.longdroid.databinding.ItemArticleSubtitleBinding
import com.example.longdroid.util.extension.ItemDiffCallback

class ArticleParagraphAdapter(
    private val addReadMark: (TextView, String, Int) -> Unit,
    private val reLoadBookMark: (TextView, String) -> Unit,
) :
    ListAdapter<ResponseArticleDto.Paragraph, ArticleParagraphViewHolder>(
        ItemDiffCallback<ResponseArticleDto.Paragraph>(
            onItemsTheSame = { old, new -> old.content == new.content },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleParagraphViewHolder {
        return when (viewType) {
            SUB_TITLE -> {
                val binding = ItemArticleSubtitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                ArticleParagraphViewHolder.SubTitleArticleViewHolder(binding)
            }

            BODY -> {
                val binding = ItemArticleBodyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                ArticleParagraphViewHolder.BodyArticleViewHolder(
                    binding,
                    addReadMark,
                    reLoadBookMark,
                )
            }

            else -> throw IllegalArgumentException(UNKNOWN_TYPE)
        }
    }

    override fun onBindViewHolder(holder: ArticleParagraphViewHolder, position: Int) {
        when (holder) {
            is ArticleParagraphViewHolder.SubTitleArticleViewHolder -> holder.onBind(
                getItem(
                    position,
                ),
            )

            is ArticleParagraphViewHolder.BodyArticleViewHolder -> holder.onBind(
                getItem(position),
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).paragraphType) {
            SUB_HEADING -> SUB_TITLE
            TEXT -> BODY
            else -> throw IllegalArgumentException(UNKNOWN_TYPE)
        }
    }

    companion object {
        private const val SUB_TITLE = 0
        private const val BODY = 1
        private const val SUB_HEADING = "SUBHEADING"
        private const val TEXT = "TEXT"
        private const val UNKNOWN_TYPE = "Unknown viewType"
    }
}
