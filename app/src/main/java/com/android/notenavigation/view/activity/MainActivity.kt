package com.android.notenavigation.view.activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.notenavigation.R
import com.android.notenavigation.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity :DaggerAppCompatActivity (){
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        bindingView()
        setupNavigation()
    }
    private fun bindingView(){
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

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
