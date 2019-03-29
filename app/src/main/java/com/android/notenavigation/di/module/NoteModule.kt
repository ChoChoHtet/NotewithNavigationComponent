package com.android.notenavigation.di.module

import com.android.notenavigation.NoteManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NoteModule {
    @Provides
    @Singleton
    fun provideNoteManger(noteManager:NoteManager):NoteManager=noteManager
}