package com.android.notenavigation.di.module

import android.content.Context
import com.android.notenavigation.NoteApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    @JvmStatic
    fun applicationContext(noteApp: NoteApp):Context=noteApp
}