package com.ilyko.nytimes.repository

import com.ilyko.nytimes.model.ArticleDto
import com.ilyko.nytimes.model.mappers.ArticleMapper
import com.ilyko.nytimes.storage.api.WebService
import com.ilyko.nytimes.storage.db.NYTimesDao
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val webService: WebService,
    private val nyTimesDao: NYTimesDao,
    private val articleMapper: ArticleMapper
) : Repository {
    override fun loadFavoriteArticles(): Single<List<ArticleDto>> {
        return Single.create { result ->
            nyTimesDao.getFavorites().subscribe({
                result.onSuccess(it)
            }, {
                result.onError(it)
            })
        }
    }

    override fun findArticleById(id: Long): Boolean {
        return nyTimesDao.findArticleById(id).subscribeOn(Schedulers.io()).isEmpty.blockingGet()
    }

    override fun loadArticles(type: String): Single<List<ArticleDto>> {
        return webService.getArticles(type).map {
            it.results
        }.map { it ->
            it.map { articleMapper.map(it) }
        }
    }

    private fun addToFavorite(article: ArticleDto) {
        nyTimesDao.insertArticle(article).subscribeOn(Schedulers.io()).subscribe()
    }

    private fun removeFromFavorite(article: ArticleDto) {
        nyTimesDao.deleteArticle(article.id).subscribeOn(Schedulers.io()).subscribe()
    }

    override fun onFavoriteEvent(article: ArticleDto): Boolean {
        if (findArticleById(article.id)) {
            addToFavorite(article)
        } else {
            removeFromFavorite(article)
        }
        return !findArticleById(article.id)
    }

}