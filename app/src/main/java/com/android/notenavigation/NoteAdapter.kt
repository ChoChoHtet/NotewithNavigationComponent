package com.android.notenavigation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.notenavigation.db.Note1


class NoteAdapter(private val eventListener: NoteViewHolder.EventListener) : RecyclerView.Adapter<NoteViewHolder>() {
    private var noteList= emptyList<Note1>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view,eventListener)
    }

    override fun getItemCount(): Int=noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note=noteList[position]
        holder.bind(note)
    }
    fun setNoteData(notes:List<Note1>){
        noteList=notes
    }
}