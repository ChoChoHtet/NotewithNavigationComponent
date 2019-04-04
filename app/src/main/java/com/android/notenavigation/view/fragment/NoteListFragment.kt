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
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.notenavigation.*
import com.android.notenavigation.databinding.FragmentNoteListBinding
import com.android.notenavigation.db.Note1
import com.android.notenavigation.event.EventListener
import com.android.notenavigation.event.EventObserver
import com.android.notenavigation.viewmodel.NoteListViewModel
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject


class NoteListFragment : DaggerFragment(), NoteViewHolder.EventListener {
    //TODO BEST SOLUTION
    override fun toNoteDetailActivity(note: Note1) {
        //pass id
        val action = NoteListFragmentDirections.actionNoteDetail(note.id)
        //findNavController().navigate(action)
        binding.navDirection=action
        viewModel.setDestination(binding.navDirection!!.actionId)
    }

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: NoteListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentNoteListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NoteListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        setupRecyclerView()
        //load init data
        viewModel.loadNoteList()
        //observe data changes
        viewModel.observeResponse.observe(this, Observer {
            it?.let { noteList ->
                processResponse(noteList)
            }
        })

        viewModel.getDestination.observe(this, EventObserver(object : EventListener<Int> {
            override fun onEvent(destinationId: Int?) {
                if (destinationId != null)
                    addNewDestination(destinationId)
            }

        }))

    }

    private fun addNewDestination(id: Int) {
        findNavController().navigate(id,binding.navDirection?.arguments)
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val action = NoteListFragmentDirections.actionAddNote()
        binding.navDirection = action
    }

    private fun processResponse(noteList: List<Note1>) {
         viewModel.size.set(noteList.size)
        if (noteList.isNotEmpty()) {
            noteAdapter.setNoteData(noteList)
            noteAdapter.notifyDataSetChanged()
        }

    }

    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter(this)
        binding.noteList.layoutManager = LinearLayoutManager(context)
        binding.noteList.setHasFixedSize(true)
        binding.noteAdapter=noteAdapter
    }


}
