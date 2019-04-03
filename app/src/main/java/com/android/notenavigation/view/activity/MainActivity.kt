package com.android.notenavigation.view.activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.notenavigation.R
import com.android.notenavigation.databinding.ActivityMainBinding
import com.android.notenavigation.event.EventListener
import com.android.notenavigation.event.EventObserver
import com.android.notenavigation.view.fragment.NoteListFragmentDirections
import com.android.notenavigation.viewmodel.NoteListViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity :DaggerAppCompatActivity (){
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NoteListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
        setupNavigation()
        viewModel.getDestination.observe(this, EventObserver(object : EventListener<Int> {
            override fun onEvent(t: Int?) {
                addNewDestination(t!!)
            }

        }))

    }
    private fun bindingView(){
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel=ViewModelProviders.of(this,viewModelFactory).get(NoteListViewModel::class.java)
        binding.viewModel=viewModel
        binding.lifecycleOwner = this

    }
    private fun addNewDestination(id:Int){
       navController.navigate(id)
    }

    private fun setupNavigation() {
         navController=findNavController(R.id.nav_host_fragment)
        /**
         * action bar title will be automatically updated when destination changes
         * up button will be displayed when on a non-root destination place
         */
        setupActionBarWithNavController(navController)
    }

    /**
     * NavHostFragment intercept the system back button
     */
    override fun onSupportNavigateUp()=navController.navigateUp()
}
