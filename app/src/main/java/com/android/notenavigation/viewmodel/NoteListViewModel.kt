package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.notenavigation.Note
import com.android.notenavigation.NoteManager

class NoteListViewModel : ViewModel() {
    private var noteList = MutableLiveData<List<Note>>()
    val observeResponse: LiveData<List<Note>>
        get() = noteList

    fun loadNoteList() {
        noteList.value = NoteManager.getNoteList()
    }
}