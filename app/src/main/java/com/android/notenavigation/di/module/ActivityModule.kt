package com.android.notenavigation.di.module

import com.android.notenavigation.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun mainActivity():MainActivity
}