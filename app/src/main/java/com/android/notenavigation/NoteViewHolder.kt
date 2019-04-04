package com.android.notenavigation

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.android.notenavigation.databinding.NoteItemBinding
import com.android.notenavigation.db.Note1
import kotlinx.android.synthetic.main.note_item.view.*


class NoteViewHolder(private val binding: NoteItemBinding, private val eventListener: EventListener) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Note1) {
        binding.note = data
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            eventListener.toNoteDetailActivity(data)
        }
    }

    interface EventListener {
        fun toNoteDetailActivity(note: Note1)
    }

}