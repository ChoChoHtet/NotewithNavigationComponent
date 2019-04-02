package com.android.notenavigation

import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.notenavigation.databinding.NoteItemBinding
import com.android.notenavigation.db.Note1
import kotlinx.android.synthetic.main.note_item.view.*


class NoteViewHolder(private val binding: NoteItemBinding,private val eventListener: EventListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Note1) {
        /*itemView.tvNumber.text = data.id.toString()
        itemView.tvTitle.text = data.title
        //click item to view detail
        itemView.setOnClickListener {
           // Toast.makeText(itemView.context,adapterPosition.toString(),Toast.LENGTH_LONG).show()
            itemListener.toNoteDetailActivity(data)
        }*/
        binding.note=data
        //binding.executePendingBindings()
    }

    interface EventListener {
        fun toNoteDetailActivity(note: Note1)
    }

}