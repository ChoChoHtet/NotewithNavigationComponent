package com.android.notenavigation.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.support.annotation.IdRes
import android.util.Log
import android.widget.Toast
import com.android.notenavigation.NoteApp
import com.android.notenavigation.db.Note1
import com.android.notenavigation.db.NoteDao
import javax.inject.Inject

class NoteListViewModel @Inject constructor(private val noteDao: NoteDao) : ViewModel() {
    private var noteList = MutableLiveData<List<Note1>>()
    private var newDestination=MutableLiveData<Int>()
    val getNewDestination:LiveData<Int>
    get() = newDestination
    val observeResponse: LiveData<List<Note1>>
        get() = noteList
    private val direction=MutableLiveData<Boolean>()
    val getDirection:LiveData<Boolean>
    get() = direction

    fun loadNoteList() {
        noteList.value=noteDao.getNoteList()
    }
    fun actionAddNewNote(){
        Log.e("App","Aa")
        direction.value=true
    }

}