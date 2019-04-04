package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.android.notenavigation.Response
import com.android.notenavigation.db.Note1
import com.android.notenavigation.db.NoteDao
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(private val noteDao: NoteDao) : ViewModel() {
    private var status = MutableLiveData<Response>()
    var title = ObservableField<String>()

    val observeResponse: LiveData<Response>
        get() = status

    fun editNote(id: Int) {
        noteDao.editNote(Note1(id, title.get()))
        status.value = Response.editNote()
    }

    fun currentNote(id: Int) {
        val data = noteDao.getNote(id)
        title.set(data.title)
    }
}