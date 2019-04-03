package com.android.notenavigation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    override fun toNoteDetailActivity(note: Note1) {
        //pass id
         var navDirection= NoteListFragmentDirections.actionNoteDetail(note.id)
         view?.let {
             findNavController().navigate(navDirection)
         }
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
        noteAdapter = NoteAdapter(this)
        setupRecyclerView()
        //load init data
        viewModel.loadNoteList()
        //observe data changes
        viewModel.observeResponse.observe(this, Observer {
            it?.let { noteList ->
                processResponse(noteList)
            }
        })

      /*  viewModel.getDestination.observe(this,EventObserver(object : EventListener<Int>{
            override fun onEvent(t: Int?) {
                addNewDestination(t!!)
               // binding.root.findNavController().navigate(R.id.action_add_note)

            }

        }))*/

    }
    private fun addNewDestination(id:Int){
        binding.root.findNavController().navigate(id)
}

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val action=NoteListFragmentDirections.actionAddNote()
        binding.navDirection=action
        //binding.root.findNavController().navigate(action)
    }

    private fun processResponse(noteList: List<Note1>) {
        if (noteList.isEmpty()) {
            binding.noteList.visibility = View.GONE
            binding.tvNoNote.visibility = View.VISIBLE
        } else {
            binding.noteList.visibility = View.VISIBLE
            binding.tvNoNote.visibility = View.GONE
            noteAdapter.setNoteData(noteList)
            noteAdapter.notifyDataSetChanged()
        }

    }

    private fun setupRecyclerView() {
        binding.noteList.layoutManager =LinearLayoutManager(context)
        binding.noteList.setHasFixedSize(true)
        binding.noteList.adapter = noteAdapter
    }


}
