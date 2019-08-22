package com.ilyko.nytimes.ui.main

import android.util.Log
import androidx.lifecycle.*

import com.ilyko.nytimes.repository.ArticleRepository
import com.ilyko.nytimes.model.ArticleDto
import com.ilyko.nytimes.model.NetworkState
import com.ilyko.nytimes.ui.common.BaseViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PageViewModel @Inject constructor(private val repository: ArticleRepository) : BaseViewModel() {

    private val _articles = MutableLiveData<List<ArticleDto>>()
    val articles: LiveData<List<ArticleDto>> = _articles

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> = _networkState
    private val _initialLoad = MutableLiveData<NetworkState>()
    val initialLoad: LiveData<NetworkState> = _initialLoad

    private var retryCompletable: Completable? = null

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun loadArticles(type: String) {
        addDisposables(repository.loadArticles(type).subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    _networkState.postValue(NetworkState.LOADING)
                    _initialLoad.postValue(NetworkState.LOADING)
                }
                .doOnTerminate {
                    _networkState.postValue(NetworkState.LOADED)
                    _initialLoad.postValue(NetworkState.LOADED)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _articles.postValue(it)
                    setRetry(null)
                }, {
                    it.printStackTrace()
                    val error = NetworkState.error(it.message)
                    _networkState.postValue(error)
                    _initialLoad.postValue(error)
                    setRetry(Action { loadArticles(type) })
                })
        )
    }

    fun retry() {
        if (retryCompletable != null) {
            addDisposables(
                    retryCompletable!!
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ }, { throwable -> Log.d(this.javaClass.canonicalName, throwable.message) })
            )
        }
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }
}