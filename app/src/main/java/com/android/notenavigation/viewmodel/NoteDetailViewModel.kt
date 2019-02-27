package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.notenavigation.NoteManager
import com.android.notenavigation.Response

class NoteDetailViewModel:ViewModel() {
    private val note=MutableLiveData<Response>()
    val observeNote:LiveData<Response>
    get()=note
    fun getNote(id:Int){
        val data=NoteManager.getNote(id)
        note.value=Response.viewNote(data)
    }
    fun deleteNote(id:Int){
        NoteManager.deleteNote(id)
        note.value=Response.delete()
    }
}