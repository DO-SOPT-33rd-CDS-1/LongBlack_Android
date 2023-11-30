package com.example.longdroid.presentation.article

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ImageSpan
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.longdroid.R
import com.example.longdroid.data.datasource.LongBlackStorage
import com.example.longdroid.data.model.response.ResponseArticleDto
import com.example.longdroid.databinding.ActivityArticleBinding
import com.example.longdroid.util.binding.BindingActivity
import com.example.longdroid.util.extension.setOnSingleClickListener

class ArticleActivity : BindingActivity<ActivityArticleBinding>(R.layout.activity_article) {
    private val articleViewModel: ArticlelViewModel by viewModels()
    private val articleTitleAdapter by lazy {
        ArticleTitleAdapter(clickStamp = { postStamp() })
    }

    private val articleParagraphAdapter by lazy {
        ArticleParagraphAdapter(
            addReadMark = this::addReadMark,
            reLoadBookMark = this::appendImageToTextView,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAdapter()
        fetchArticle()
        checkScrollToBookMark()
        putLikeState()
        clickBookMark()
    }

    private fun setAdapter() {
        val articleAdapter = ConcatAdapter(articleTitleAdapter, articleParagraphAdapter)
        binding.rvArticleTitle.adapter = articleAdapter
    }

    private fun fetchArticle() {
        articleViewModel.getArticleData(1)
        articleViewModel.articleData.observe(this, this::onArticleDataChanged)
    }

    private fun checkScrollToBookMark() {
        if (LongBlackStorage.bookMarkIdx != -1) {
            binding.rvArticleTitle.post {
                smoothScrollToBookmark()
            }
        }
    }

    private fun smoothScrollToBookmark() {
        val layoutManager = (binding.rvArticleTitle.layoutManager as LinearLayoutManager)
        LongBlackStorage.bookMarkIdx?.let {
            layoutManager.smoothScrollToPosition(
                binding.rvArticleTitle,
                RecyclerView.State(),
                it,
            )
        }
    }

    private fun addReadMark(textView: TextView, clickedText: String, position: Int) {
        if (isTransparentBackgroundSet()) {
            appendImageToTextView(textView, clickedText)
            postBookMark(position)
        }
    }

    private fun postBookMark(bookMarkIdx: Int) {
        articleViewModel.postBookMarkState(1, bookMarkIdx)
        LongBlackStorage.bookMarkIdx = bookMarkIdx
        updateBookMarkUI(LongBlackStorage.bookMarkIdx)
        setDefaultBackground()
    }

    private fun postStamp() {
        articleViewModel.postStampState(1)
        fetchArticle()
    }

    private fun putLikeState() {
        binding.ivArticleLike.setOnSingleClickListener {
            articleViewModel.putLikeState(1, false)
            fetchArticle()
        }
    }

    private fun onArticleDataChanged(articleData: ResponseArticleDto) {
        articleTitleAdapter.submitList(listOf(articleData))
        articleParagraphAdapter.submitList(articleData.paragraphs)
        updateLikeUI(articleData.like)
        updateBookMarkUI(articleData.bookmarkIdx)
    }

    private fun clickBookMark() {
        binding.ivArticleReadMark.setOnSingleClickListener {
            handleBookMarkClick()
        }
    }

    private fun handleBookMarkClick() {
        if (LongBlackStorage.bookMarkIdx != -1) {
            handleDeleteBookMarkClick()
        } else {
            setTransparentBackground()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleDeleteBookMarkClick() {
        articleViewModel.deleteBookMarkState(1)
        articleParagraphAdapter.notifyDataSetChanged()
        LongBlackStorage.bookMarkIdx = -1
        updateBookMarkUI(LongBlackStorage.bookMarkIdx)
    }

    private fun setDefaultBackground() {
        binding.clArticleBody.foreground = null
    }

    private fun setTransparentBackground() {
        binding.clArticleBody.foreground =
            ContextCompat.getDrawable(this, R.color.transparent_background)
    }

    private fun isTransparentBackgroundSet(): Boolean {
        return binding.clArticleBody.foreground != null
    }

    private fun updateLikeUI(isLike: Boolean) {
        binding.ivArticleLike.load(if (isLike) R.drawable.ic_book_mark_on_small else R.drawable.ic_book_mark_off_small)
    }

    private fun updateBookMarkUI(isBookMark: Int?) {
        binding.ivArticleReadMark.load(if (isBookMark != -1) R.drawable.ic_btn_delete_read_mark else R.drawable.ic_btn_add_read_mark)
    }

    private fun appendImageToTextView(textView: TextView, clickedText: String) {
        val imageDrawable = ContextCompat.getDrawable(this, R.drawable.ic_read_mark)
        imageDrawable?.setBounds(0, 0, imageDrawable.intrinsicWidth, imageDrawable.intrinsicHeight)

        val imageStartIndex = clickedText.indexOf("\n\n")

        if (imageDrawable != null && imageStartIndex != -1) {
            val imageSpan = ImageSpan(imageDrawable, ImageSpan.ALIGN_CENTER)
            val spannableString = SpannableString(clickedText).apply {
                setSpan(
                    imageSpan,
                    imageStartIndex - 1,
                    imageStartIndex,
                    SpannableString.SPAN_INCLUSIVE_EXCLUSIVE,
                )
            }
            textView.text = spannableString
        }
    }
}
