package com.android.notenavigation.view.fragment



import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.notenavigation.Note
import com.android.notenavigation.NoteStatus
import com.android.notenavigation.R
import com.android.notenavigation.Response
import com.android.notenavigation.db.Note1
import com.android.notenavigation.viewmodel.NoteDetailViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_note_detail.*
import javax.inject.Inject

class NoteDetailFragment :DaggerFragment() {
    private lateinit var viewModel:NoteDetailViewModel
    /**
     * type safe argument
     * get bundle note data
     */
    private val noteId by lazy {
        NoteDetailFragmentArgs.fromBundle(arguments!!).noteId
    }
    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProviders.of(this,viewModelFactory).get(NoteDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNote(noteId)
        viewModel.observeNote.observe(this, Observer {
            it?.let {response->
                processNote(response)
            }
        })
        //delete note
        btDeleteNote.setOnClickListener {
            viewModel.deleteNote(noteId)
        }
        //edit note
        btEditNote.setOnClickListener {
            val navDirection=NoteDetailFragmentDirections.actionEditNote(noteId)
            findNavController().navigate(navDirection)
        }
    }

    private fun processNote(response: Response) {
      when(response.status){
          NoteStatus.VIEW->viewNote(response.note)
          NoteStatus.DELETE-> view?.let {
              //back to main
              findNavController().popBackStack(R.id.noteListFragment,false)
          }
      }
    }
    private fun viewNote(note: Note1?){
        view?.let {
            Title.text=note!!.title
            note_Id.text=note!!.id.toString()
        }

    }

}
