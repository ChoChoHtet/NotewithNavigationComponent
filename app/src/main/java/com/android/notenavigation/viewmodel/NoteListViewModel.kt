package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.IdRes
import android.util.Log
import com.android.notenavigation.db.Note1
import com.android.notenavigation.db.NoteDao
import com.android.notenavigation.event.Event
import javax.inject.Inject

class NoteListViewModel @Inject constructor(private val noteDao: NoteDao) : ViewModel() {
    private var noteList = MutableLiveData<List<Note1>>()
    private var destination = MutableLiveData<Event<Int>>()
    val getDestination: LiveData<Event<Int>>
        get() = destination
    val observeResponse: LiveData<List<Note1>>
        get() = noteList

    fun loadNoteList() {
        noteList.value = noteDao.getNoteList()
    }

    fun setDestination(id: Int) {
        destination.value = Event(id)
    }
}