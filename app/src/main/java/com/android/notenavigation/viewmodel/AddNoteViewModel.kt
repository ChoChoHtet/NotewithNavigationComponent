package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.notenavigation.NoteManager

class AddNoteViewModel:ViewModel() {
    private val status=MutableLiveData<Boolean>()
    val observeResponse:LiveData<Boolean>
    get() = status
    //add  note
    fun addNote(text:String){
        status.value=try {
            NoteManager.addNote(text)
            true
        }catch (e :IllegalArgumentException){
            false
        }

    }

}