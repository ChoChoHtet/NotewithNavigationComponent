package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
import com.android.notenavigation.Response
import com.android.notenavigation.db.NoteDao
import com.android.notenavigation.event.Event
import javax.inject.Inject

class NoteDetailViewModel @Inject constructor(private val noteDao: NoteDao):ViewModel() {
    private val note=MutableLiveData<Response>()
    val title=ObservableField<String>()
    val noteId=ObservableInt()
    val observeNote:LiveData<Response>
    get()=note
    fun getNote(id:Int){
        val data=noteDao.getNote(id)
        title.set(data.title)
        noteId.set(data.id)
    }
    fun deleteNote(){
        noteDao.deleteNote(noteId.get())
        note.value=Response.delete()
    }
    private var destination = MutableLiveData<Event<Int>>()
    val getDestination: LiveData<Event<Int>>
        get() = destination

    fun setDestination(id: Int) {
        destination.value = Event(id)
    }
}