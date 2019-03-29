package com.android.notenavigation.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Note1::class],version = 1,exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){
    abstract fun noteDao():NoteDao
}