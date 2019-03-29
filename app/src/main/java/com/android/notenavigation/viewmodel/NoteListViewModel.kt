package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.notenavigation.db.Note1
import com.android.notenavigation.db.NoteDao
import javax.inject.Inject

class NoteListViewModel @Inject constructor(private val noteDao: NoteDao) : ViewModel() {
    private var noteList = MutableLiveData<List<Note1>>()
    val observeResponse: LiveData<List<Note1>>
        get() = noteList

    fun loadNoteList() {
        /*noteList.value = noteManger.getNoteList()*/
        noteList.value=noteDao.getNoteList()
    }
}