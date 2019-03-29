package com.android.notenavigation.di.module

import android.content.Context
import com.android.notenavigation.NoteApp
import dagger.Module
import dagger.Provides

@Module
object AppModule {
    @Provides
    @JvmStatic
    fun applicationContext(noteApp: NoteApp):Context=noteApp
}