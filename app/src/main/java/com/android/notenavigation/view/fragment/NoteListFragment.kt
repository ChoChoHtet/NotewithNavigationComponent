package com.android.notenavigation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.notenavigation.*
import com.android.notenavigation.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.fragment_note_detail.*
import kotlinx.android.synthetic.main.fragment_note_list.*

class NoteListFragment : Fragment(), NoteViewHolder.EventListener {
    override fun toNoteDetailActivity(note: Note) {
        //pass id
        var navDirection= NoteListFragmentDirections.ActionNoteDetail(note.id)
        view?.let {
            findNavController().navigate(navDirection)
        }
    }

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: NoteListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(NoteListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteAdapter= NoteAdapter(this)
       setupRecyclerView()
        /**
         * navigate to add note screen
         */
        fab_add_note.setOnClickListener {
            findNavController().navigate(R.id.action_add_note)
        }
        //load init data
        viewModel.loadNoteList()
        //observe data changes
        viewModel.observeResponse.observe(this, Observer {
            it?.let {noteList->
                processResponse(noteList)
            }
        })

    }

    private fun processResponse(noteList: List<Note>) {
        if (noteList.isEmpty()){
            note_list.visibility=View.GONE
            tvNoNote.visibility=View.VISIBLE
        }else{
            tvNoNote.visibility=View.GONE
            note_list.visibility=View.VISIBLE
            noteAdapter.setNoteData(noteList)
            noteAdapter.notifyDataSetChanged()
        }

    }

    private fun setupRecyclerView() {
        note_list.layoutManager=LinearLayoutManager(context)
        note_list.setHasFixedSize(true)
        note_list.adapter=noteAdapter
    }


}
