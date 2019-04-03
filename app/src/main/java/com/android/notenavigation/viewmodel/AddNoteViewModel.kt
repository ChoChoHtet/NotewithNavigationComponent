package com.android.notenavigation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.android.notenavigation.Note
import com.android.notenavigation.db.Note1
import com.android.notenavigation.db.NoteDao
import com.android.notenavigation.event.Event
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(private val noteDao: NoteDao):ViewModel() {
    private val status=MutableLiveData<Boolean>()
    var title=ObservableField<String>()

    private var destination = MutableLiveData<Event<Int>>()
    val getDestination: LiveData<Event<Int>>
        get() = destination

    fun setDestination(id: Int) {
        destination.value = Event(id)
    }

    val observeResponse:LiveData<Boolean>
    get() = status
    //add  note
    fun addNote(){
        status.value=try {
            noteDao.addNote(Note1(0,title.get()))
            true
        }catch (e :IllegalArgumentException){
            false
        }
       // destination.value=Event(id)

    }

}