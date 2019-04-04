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
import com.android.notenavigation.databinding.FragmentNoteDetailBinding
import com.android.notenavigation.event.EventListener
import com.android.notenavigation.event.EventObserver
import com.android.notenavigation.viewmodel.NoteDetailViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NoteDetailFragment : DaggerFragment() {
    private lateinit var viewModel: NoteDetailViewModel
    private lateinit var binding: FragmentNoteDetailBinding
    private var bundle: Bundle? = null
    /**
     * type safe argument
     * get bundle note data
     */
    private val noteId by lazy {
        NoteDetailFragmentArgs.fromBundle(arguments!!).noteId
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NoteDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        viewModel.getNote(noteId)
        viewModel.observeNote.observe(this, Observer {
            it?.let { response ->
                processNote(response)
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
        bundle = binding.navDirection?.arguments
        if (bundle == null)
            findNavController().navigate(id)
        else {
            findNavController().navigate(id,bundle!!)
        }
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val action = NoteDetailFragmentDirections.actionEditNote(noteId)
        binding.navDirection = action

    }

    private fun processNote(response: Response) {
        when (response.status) {
            NoteStatus.DELETE -> view?.let {
                //back to main
                findNavController().popBackStack(R.id.noteListFragment, false)
            }
        }
    }


}
