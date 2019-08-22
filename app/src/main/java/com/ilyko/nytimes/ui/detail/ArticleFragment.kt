package com.ilyko.nytimes.ui.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ilyko.nytimes.R
import com.ilyko.nytimes.databinding.FragmentArticleBinding
import com.ilyko.nytimes.model.ArticleDto
import com.ilyko.nytimes.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment :
    BaseFragment<ArticleViewModel, FragmentArticleBinding>(ArticleViewModel::class.java, R.layout.fragment_article) {

    private lateinit var articleDto: ArticleDto

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs = ArticleFragmentArgs.fromBundle(it)
            articleDto = safeArgs.articleDto!!
            binding.article = articleDto
            viewModel.isFavorite(articleDto)
            favorite.setOnClickListener {
                viewModel.onFavoriteClick(articleDto)
            }
        }
        viewModel.favorite.observe(this, Observer {
            if(it){
                favorite.setImageResource(R.drawable.ic_star_border_black_24dp)
            } else {
                favorite.setImageResource(R.drawable.ic_star_24dp)
            }
        })
    }


    override fun onResume() {
        super.onResume()
        showBackButton()
    }

    override fun onPause() {
        super.onPause()
        hideBackButton()
    }
}



