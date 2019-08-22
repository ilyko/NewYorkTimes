package com.ilyko.nytimes.di.component

import android.content.Context
import com.ilyko.nytimes.App
import com.ilyko.nytimes.MainActivity
import com.ilyko.nytimes.di.module.ActivityModule
import com.ilyko.nytimes.di.module.FragmentModule
import com.ilyko.nytimes.di.module.NetworkModule
import com.ilyko.nytimes.di.module.RoomModule
import com.ilyko.nytimes.di.module.ViewModelModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ActivityModule::class,
            FragmentModule::class,
            ViewModelModule::class,
            NetworkModule::class,
            RoomModule::class
        ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)

}