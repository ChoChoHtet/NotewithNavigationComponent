package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.notenavigation.Response
import com.android.notenavigation.db.NoteDao
import javax.inject.Inject

class NoteDetailViewModel @Inject constructor(private val noteDao: NoteDao):ViewModel() {
    private val note=MutableLiveData<Response>()
    val observeNote:LiveData<Response>
    get()=note
    fun getNote(id:Int){
        //val data=noteManger.getNote(id)
        val data=noteDao.getNote(id)
        note.value=Response.viewNote(data)
    }
    fun deleteNote(id:Int){
        //noteManger.deleteNote(id)
        noteDao.deleteNote(id)
        note.value=Response.delete()
    }
}