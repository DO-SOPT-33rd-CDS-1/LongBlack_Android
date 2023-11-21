package com.example.longdroid.presentation.article

import android.os.Bundle
import androidx.activity.viewModels
import com.example.longdroid.R
import com.example.longdroid.databinding.ActivityArticleBinding
import com.example.longdroid.util.binding.BindingActivity
import com.example.longdroid.util.extension.setOnSingleClickListener

class ArticleActivity : BindingActivity<ActivityArticleBinding>(R.layout.activity_article) {
    private val articleViewModel: ArticlelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeBookmark()
        updateBookmarkState()
    }

    private fun updateBookmarkState() {
        binding.ivArticleBookmark.setOnSingleClickListener {
            articleViewModel.setBookMarkState()
        }
    }

    private fun observeBookmark() {
        articleViewModel.isBookMarked.observe(this) { isBookMarked ->
            updateBookmarkUI(isBookMarked)
        }
    }

    private fun updateBookmarkUI(isBookMarked: Boolean) {
        val bookMark = if (isBookMarked) {
            R.drawable.ic_book_mark_on_small
        } else {
            R.drawable.ic_book_mark_off_small
        }
        binding.ivArticleBookmark.setImageResource(bookMark)
    }
}
