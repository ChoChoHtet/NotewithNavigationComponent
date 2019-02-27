package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.notenavigation.Note
import com.android.notenavigation.NoteManager
import com.android.notenavigation.Response

class EditNoteViewModel:ViewModel() {
    private var status=MutableLiveData<Response>()

    val observeResponse:LiveData<Response>
    get() = status

    fun editNote(id:Int,text:String){
        NoteManager.editNote(id,text)
        status.value= Response.editNote()
    }
    fun currentNote(id: Int){
        val data=NoteManager.getNote(id)
        status.value= Response.viewNote(data)
    }
}