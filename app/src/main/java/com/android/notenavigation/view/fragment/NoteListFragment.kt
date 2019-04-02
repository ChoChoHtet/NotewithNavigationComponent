package com.android.notenavigation.view.fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.notenavigation.*
import com.android.notenavigation.Note
import com.android.notenavigation.databinding.FragmentNoteListBinding
import com.android.notenavigation.db.Note1
import com.android.notenavigation.viewmodel.NoteListViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_note_list.*
import javax.inject.Inject

class NoteListFragment : DaggerFragment(), NoteViewHolder.EventListener {
    /*override fun actionAddNewNote() {
        *//**
         * navigate to add note screen
         *//*
      binding.fabAddNote.setOnClickListener {
          Log.e("Action","AA")
            findNavController().navigate(R.id.action_add_note)
        }
    }*/

    override fun toNoteDetailActivity(note: Note1) {
        //pass id
        var navDirection= NoteListFragmentDirections.ActionNoteDetail(note.id)
        view?.let {
            findNavController().navigate(navDirection)
        }
    }

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: NoteListViewModel
    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory
    private lateinit var binding: FragmentNoteListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       viewModel=ViewModelProviders.of(this,viewModelFactory).get(NoteListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_note_list,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
        noteAdapter= NoteAdapter(this)
       setupRecyclerView()
        //load init data
        viewModel.loadNoteList()
        //observe data changes
        viewModel.observeResponse.observe(this, Observer {
            it?.let {noteList->
                processResponse(noteList)
            }
        })
        /*viewModel.getNewDestination.observe(this, Observer {
           it?.let {id->
               navigate(id)
           }

        })*/
        viewModel.getDirection.observe(this, Observer {
            it?.let {
                navigate()
            }
        })

    }
    private fun navigate(){
        view?.let {
            findNavController().navigate(R.id.action_add_note)
        }

    }

    private fun processResponse(noteList: List<Note1>) {
        if (noteList.isEmpty()){
            binding.noteList.visibility=View.GONE
            binding.tvNoNote.visibility=View.VISIBLE
        }else{
            binding.noteList.visibility=View.VISIBLE
            binding.tvNoNote.visibility=View.GONE
            noteAdapter.setNoteData(noteList)
            noteAdapter.notifyDataSetChanged()
        }

    }

    private fun setupRecyclerView() {
        binding.noteList.layoutManager=LinearLayoutManager(context)
        binding.noteList.setHasFixedSize(true)
        binding.noteList.adapter=noteAdapter
    }


}
