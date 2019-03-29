package com.android.notenavigation.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.android.notenavigation.db.NoteDao
import com.android.notenavigation.db.NoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * classes with @Module -> are responsible for providing objects/classes which can be injected
 * such classes define with @Provide -> returned object are available for dependency injection
 */
@Module
object DbModule {
    //return note db object
    @Provides
    @Singleton
    @JvmStatic
    fun provideDatabase(application: Application):NoteDatabase =
        Room.databaseBuilder(application,NoteDatabase::class.java,"Note.db").allowMainThreadQueries().build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideNoteDao(db:NoteDatabase):NoteDao=db.noteDao()

}