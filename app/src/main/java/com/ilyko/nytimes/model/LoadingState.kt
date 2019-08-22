package com.ilyko.nytimes.model

sealed class LoadingState<out T> {
    class Loading<out T> : LoadingState<T>()
    data class Success<out T>(val data: T) : LoadingState<T>()
    data class Failure<out T>(val throwable: Throwable) : LoadingState<T>()
}