package com.ilyko.nytimes.storage.api

import com.ilyko.nytimes.Constants
import com.ilyko.nytimes.model.Article
import com.ilyko.nytimes.model.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface WebService {

    @GET("{type}/30.json?api-key=${Constants.NYTIMES_API_KEY}")
    fun getArticles(
            @Path("type") type: String
    ): Single<BaseResponse<Article>>
}