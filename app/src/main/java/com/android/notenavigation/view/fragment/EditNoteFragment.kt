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
import com.android.notenavigation.NoteStatus

import com.android.notenavigation.R
import com.android.notenavigation.Response
import com.android.notenavigation.databinding.FragmentEditNoteBinding
import com.android.notenavigation.viewmodel.EditNoteViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EditNoteFragment : DaggerFragment() {
    private lateinit var viewModel: EditNoteViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentEditNoteBinding

    private val noteId by lazy {
        EditNoteFragmentArgs.fromBundle(arguments!!).noteId
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EditNoteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        //load current note
        viewModel.currentNote(noteId)
        viewModel.observeResponse.observe(this, Observer { response ->
            response?.let {
                processResponse(it)
            }

        })
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.id = noteId
    }

    private fun processResponse(response: Response) {
        when (response.status) {
            NoteStatus.EDIT -> view?.let {
                //back to note detail
                findNavController().popBackStack()
            }
        }

    }


}
