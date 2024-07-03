package com.example.roomdb

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.roomdb.R
import com.example.roomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        // Add the MenuProvider to the lifecycle
       // addMenuProvider(this, this, Lifecycle.State.RESUMED)

        // Set up NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

//    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//        menuInflater.inflate(R.menu.top_bar, menu)
//    }
//
//    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//        return when (menuItem.itemId) {
//            R.id.delete_button -> {
//                Toast.makeText(this, "Delete button clicked", Toast.LENGTH_SHORT).show()
//                true
//            }
//            else -> false
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
