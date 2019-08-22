package com.ilyko.nytimes.di.module

import android.content.Context
import androidx.room.Room
import com.ilyko.nytimes.repository.ArticleRepository
import com.ilyko.nytimes.storage.api.WebService
import com.ilyko.nytimes.storage.db.NYTimesDB
import com.ilyko.nytimes.storage.db.NYTimesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): NYTimesDB {
        return Room.databaseBuilder(context, NYTimesDB::class.java, "nytimes.db")
                //.addCallback(callback)
                .build()
    }

    @Singleton
    @Provides
    fun providesNYTimesDB(nyTimesDB: NYTimesDB): NYTimesDao {
        return nyTimesDB.nyTimesDao()
    }



/*    @Provides
    fun providesArticleRepository(webService: WebService, nyTimesDao: NYTimesDao): ArticleRepository {
        return ArticleRepository(webService, nyTimesDao)
    }*/

}