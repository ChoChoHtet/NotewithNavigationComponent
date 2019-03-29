package com.android.notenavigation.db

import android.arch.persistence.room.*
import com.android.notenavigation.Note

@Dao
interface NoteDao {
    //insert note
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(title: Note1)

    //update note
    @Update
    fun editNote(note: Note1)

    //delete note
    @Query("DELETE FROM Note1 WHERE id == :id")
    fun deleteNote(id: Int)

    //retrieve all note
    @Query("SELECT * FROM Note1 ")
    fun getNoteList():List<Note1>

    //retrieve note by id
    @Query("SELECT * FROM Note1 WHERE id ==:id")
    fun getNote(id:Int): Note1
}