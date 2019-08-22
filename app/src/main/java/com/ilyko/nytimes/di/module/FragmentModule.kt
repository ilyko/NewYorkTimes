package com.ilyko.nytimes.di.module

import com.ilyko.nytimes.ui.detail.ArticleFragment
import com.ilyko.nytimes.ui.favorites.FavoritesFragment
import com.ilyko.nytimes.ui.main.PageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun bindPageFragment(): PageFragment

    @ContributesAndroidInjector
    abstract fun bindArticleFragment(): ArticleFragment

    @ContributesAndroidInjector
    abstract fun bindFavoriteFragment(): FavoritesFragment

}