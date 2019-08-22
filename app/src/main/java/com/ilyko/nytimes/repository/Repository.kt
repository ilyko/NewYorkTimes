package com.ilyko.nytimes.repository

import com.ilyko.nytimes.model.ArticleDto
import io.reactivex.Maybe
import io.reactivex.Single

interface Repository {
    fun loadArticles(type: String): Single<List<ArticleDto>>

    fun loadFavoriteArticles(): Single<List<ArticleDto>>

    fun onFavoriteEvent(article: ArticleDto) : Boolean

    //fun addToFavorite(article: ArticleDto)

    //fun removeFromFavorite(article: ArticleDto)

    fun findArticleById(id: Long): Boolean
}