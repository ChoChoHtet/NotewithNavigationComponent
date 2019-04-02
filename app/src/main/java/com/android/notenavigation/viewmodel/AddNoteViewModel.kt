package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.android.notenavigation.Note
import com.android.notenavigation.db.Note1
import com.android.notenavigation.db.NoteDao
import java.util.*
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(private val noteDao: NoteDao):ViewModel() {
    private val status=MutableLiveData<Boolean>()
    val observeResponse:LiveData<Boolean>
    get() = status
    var title=ObservableField<String>()
    //add  note
    fun addNote(){
        status.value=try {
            noteDao.addNote(Note1(0,title.get()))
            true
        }catch (e :IllegalArgumentException){
            false
        }

    }

}