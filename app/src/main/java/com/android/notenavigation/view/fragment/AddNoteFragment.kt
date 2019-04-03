package com.android.notenavigation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.notenavigation.R
import com.android.notenavigation.databinding.FragmentAddNoteBinding
import com.android.notenavigation.event.EventListener
import com.android.notenavigation.event.EventObserver
import com.android.notenavigation.viewmodel.AddNoteViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_note.*
import javax.inject.Inject

class AddNoteFragment : DaggerFragment() {
    private lateinit var viewModel: AddNoteViewModel
    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_add_note,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProviders.of(this,viewModelFactory).get(AddNoteViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
        viewModel.observeResponse.observe(this, Observer {
            it?.let {response->
                processResponse(response)
            }
        })
       /* viewModel.getDestination.observe(this, EventObserver(object : EventListener<Int> {
            override fun onEvent(t: Int?) {
                addNewDestination(t!!)
            }

        }))*/
    }
    private fun addNewDestination(id:Int){
        binding.root.findNavController().navigate(id)
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
