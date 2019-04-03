package com.android.notenavigation

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.notenavigation.databinding.NoteItemBinding
import com.android.notenavigation.db.Note1


class NoteAdapter(private val eventListener: NoteViewHolder.EventListener) : RecyclerView.Adapter<NoteViewHolder>() {
    private var noteList= emptyList<Note1>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding=DataBindingUtil.inflate<NoteItemBinding>(LayoutInflater.from(parent.context),R.layout.note_item,parent,false)
        return NoteViewHolder(binding,eventListener)
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