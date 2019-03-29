package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.notenavigation.Note
import com.android.notenavigation.db.Note1
import com.android.notenavigation.db.NoteDao
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(private val noteDao: NoteDao):ViewModel() {
    private val status=MutableLiveData<Boolean>()
    val observeResponse:LiveData<Boolean>
    get() = status
    private var i=1
    //add  note
    fun addNote(text:String){
        status.value=try {
            noteDao.addNote(Note1(0,text))
            //noteManger.addNote(text)
            //Log.e("Add Note","$text ${noteManger.getNote(1)!!.title}")
            true
        }catch (e :IllegalArgumentException){
            false
        }

    }

}