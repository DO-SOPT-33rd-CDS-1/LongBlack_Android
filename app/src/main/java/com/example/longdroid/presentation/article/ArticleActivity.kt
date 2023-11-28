package com.example.longdroid.presentation.article

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ConcatAdapter
import coil.load
import com.example.longdroid.R
import com.example.longdroid.databinding.ActivityArticleBinding
import com.example.longdroid.util.binding.BindingActivity
import com.example.longdroid.util.extension.setOnSingleClickListener

class ArticleActivity : BindingActivity<ActivityArticleBinding>(R.layout.activity_article) {
    private val articleViewModel: ArticlelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeLike()
        observeBookMark()
        setupClickListeners()

        articleViewModel.getArticleData(1)

        val articleTitleAdapter = ArticleTitleAdapter()
        val articleMainAdapter = ArticleParagraphAdapter()
        val articleAdapter = ConcatAdapter(articleTitleAdapter, articleMainAdapter)
        binding.rvArticleTitle.adapter = articleAdapter

        articleViewModel.articleData.observe(this) { articleData ->
            articleTitleAdapter.submitList(listOf(articleData))
            articleMainAdapter.submitList(articleData.paragraphs)
        }
    }

    private fun setupClickListeners() {
        with(binding) {
            ivArticleLike.setOnSingleClickListener {
                articleViewModel.setLikeState()
                articleViewModel.putLikeState(1, false)
            }
            ivArticleReadMark.setOnSingleClickListener {
                setTransparentBackground()

                // TODO 나중에 어댑터에서 문단 클릭 이벤트 처리 이후로 옴길 예정
                // articleViewModel.setBookMarkState()
            }
        }
    }

    private fun setTransparentBackground() {
        binding.clArticleBody.foreground =
            ContextCompat.getDrawable(this, R.color.transparent_background)
    }

    private fun observeBookMark() {
        articleViewModel.isBookMarked.observe(this) { isBookMark ->
            updateBookMarkUI(isBookMark)
        }
    }

    private fun observeLike() {
        articleViewModel.isLike.observe(this) { isArticleLike ->
            updateLikeUI(isArticleLike)
        }
    }

    private fun updateLikeUI(isLike: Boolean) {
        val likeImage = if (isLike) {
            R.drawable.ic_book_mark_on_small
        } else {
            R.drawable.ic_book_mark_off_small
        }
        binding.ivArticleLike.load(likeImage)
    }

    private fun updateBookMarkUI(isBookMark: Boolean) {
        val bookMarkImage = if (isBookMark) {
            R.drawable.ic_btn_delete_read_mark
        } else {
            R.drawable.ic_btn_add_read_mark
        }
        binding.ivArticleReadMark.load(bookMarkImage)
    }
}
