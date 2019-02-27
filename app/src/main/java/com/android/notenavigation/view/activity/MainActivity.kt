package com.android.notenavigation.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.notenavigation.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        var navController=findNavController(R.id.nav_host_fragment)
        /**
         * action bar title will be automatically updated when destination changes
         * up button will be displayed when on a non-root destination place
         */
        setupActionBarWithNavController(navController)
    }

    /**
     * NavHostFragment intercept the system back button
     */
    override fun onSupportNavigateUp()=findNavController(R.id.nav_host_fragment).navigateUp()
}
