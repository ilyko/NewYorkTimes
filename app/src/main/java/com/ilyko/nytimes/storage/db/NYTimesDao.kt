package com.ilyko.nytimes.storage.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilyko.nytimes.model.ArticleDto
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface NYTimesDao {

    @Query("SELECT * FROM favorites WHERE id = :id")
    fun findArticleById(id: Long): Maybe<ArticleDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(articleDto: ArticleDto) : Completable

    @Query("DELETE FROM favorites WHERE id = :id")
    fun deleteArticle(id: Long) : Completable

    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flowable<List<ArticleDto>>
}