package com.android.notenavigation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
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
import com.android.notenavigation.viewmodel.EditNoteViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_edit_note.*
import javax.inject.Inject

class EditNoteFragment : DaggerFragment() {
private lateinit var viewModel: EditNoteViewModel
    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory

    private val noteId by lazy {
        EditNoteFragmentArgs.fromBundle(arguments!!).noteId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProviders.of(this,viewModelFactory).get(EditNoteViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //load current note
        viewModel.currentNote(noteId)
        viewModel.observeResponse.observe(this, Observer {response->
            response?.let {
                processResponse(it)
            }

        })
        btUpdate.setOnClickListener {
            viewModel.editNote(noteId,ed_update_text.text.toString())
        }

    }

    private fun processResponse(response: Response) {
        when(response.status){
            NoteStatus.EDIT-> view?.let {
                //back to note detail
                findNavController().popBackStack()
            }
            NoteStatus.VIEW-> loadCurrentNote(response.note!!)
        }

    }
    private fun loadCurrentNote(note: Note1){
        view?.let {
            ed_update_text.setText(note.title)
        }
    }


}
