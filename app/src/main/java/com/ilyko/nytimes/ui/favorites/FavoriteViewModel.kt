package com.ilyko.nytimes.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilyko.nytimes.model.ArticleDto
import com.ilyko.nytimes.repository.ArticleRepository
import com.ilyko.nytimes.ui.common.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val repository: ArticleRepository): BaseViewModel(){
    private val _articles = MutableLiveData<List<ArticleDto>>()
    val articles: LiveData<List<ArticleDto>> = _articles

    fun loadFavoriteArticles() {
        addDisposables(repository.loadFavoriteArticles().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _articles.postValue(it)
            }, {
                it.printStackTrace()
            })
        )
    }
}