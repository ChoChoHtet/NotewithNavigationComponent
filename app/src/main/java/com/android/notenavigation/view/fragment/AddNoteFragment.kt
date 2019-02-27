package com.android.notenavigation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.notenavigation.R
import com.android.notenavigation.viewmodel.AddNoteViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*

class AddNoteFragment : Fragment() {
    private lateinit var viewModel: AddNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(AddNoteViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeResponse.observe(this, Observer {
            it?.let {response->
                processResponse(response)
            }
        })
        //save note
        btnSaveNote.setOnClickListener {
            Toast.makeText(context,"Save Note successfully",Toast.LENGTH_SHORT).show()
            viewModel.addNote(edTitle.text.toString())

        }
    }
    private fun processResponse(status:Boolean){
        when(status){
            true-> {
                view.let {
                    findNavController().popBackStack()
                }
            }

        }
    }


}
