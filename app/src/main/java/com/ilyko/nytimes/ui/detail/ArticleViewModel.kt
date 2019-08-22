package com.ilyko.nytimes.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilyko.nytimes.model.ArticleDto
import com.ilyko.nytimes.repository.ArticleRepository
import com.ilyko.nytimes.ui.common.BaseViewModel
import javax.inject.Inject

class ArticleViewModel @Inject constructor(private val repository: ArticleRepository) : BaseViewModel() {

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _favorite

    fun onFavoriteClick(article: ArticleDto) {
        _favorite.value = repository.onFavoriteEvent(article)
    }

    fun isFavorite(article: ArticleDto) {
        _favorite.value = repository.findArticleById(article.id)
    }
}