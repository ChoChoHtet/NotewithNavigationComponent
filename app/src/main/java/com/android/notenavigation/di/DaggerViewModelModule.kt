package com.android.notenavigation.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * DaggerViewModelModule -> is used to provide a map of view model through dagger by View Model Factory
 */
@Module
abstract class DaggerViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory):ViewModelProvider.Factory
}