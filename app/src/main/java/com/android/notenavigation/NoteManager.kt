package com.android.notenavigation

import java.lang.IllegalStateException

object NoteManager {
    private val notes= mutableMapOf<Int, String>()
    //add note
    fun addNote(text:String){
        validateText(text)
        val id= getNextId()
        notes[id]=text
    }
    //get note list
    fun getNoteList(): List<Note> = notes.map { Note(it.key, it.value) }

    //get note by id
    fun getNote(id:Int):Note? = notes.filter { it.key==id }
        .map { Note(it.key,it.value) }
        .firstOrNull()

    //delete note by id
    fun deleteNote(id:Int)= notes.remove(id)?:IllegalStateException("Note was not removed!!")

    //edit note by id
    fun editNote(id: Int,text:String){
        validateText(text)
        notes[id]=text
    }
    //get next index
    private fun getNextId():Int = notes.count()+1


    //text cannot be blank
    private fun validateText(text:String){
        require(text.isNotBlank()){"Note text cannot be blank"}
    }
}