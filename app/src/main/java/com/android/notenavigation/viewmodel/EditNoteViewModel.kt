package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.notenavigation.NoteManager
import com.android.notenavigation.Response
import com.android.notenavigation.db.Note1
import com.android.notenavigation.db.NoteDao
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(private val noteDao: NoteDao):ViewModel() {
    private var status=MutableLiveData<Response>()

    val observeResponse:LiveData<Response>
    get() = status

    fun editNote(id:Int,text:String){
        //noteManager.editNote(id,text)
        noteDao.editNote(Note1(id,text))
        status.value= Response.editNote()
    }
    fun currentNote(id: Int){
       // val data=noteManager.getNote(id)
        val data=noteDao.getNote(id)
        status.value= Response.viewNote(data)
    }
}