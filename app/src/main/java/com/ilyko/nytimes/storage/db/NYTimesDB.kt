package com.ilyko.nytimes.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilyko.nytimes.model.ArticleDto
import java.io.Serializable

@Database(entities = [ArticleDto::class], version = NYTimesDB.VERSION)
abstract class NYTimesDB : RoomDatabase(), Serializable {

    abstract fun nyTimesDao(): NYTimesDao

    companion object {
        const val VERSION = 1
    }
}