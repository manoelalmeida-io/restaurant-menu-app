package com.example.restaurantmenu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.restaurantmenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    setSupportActionBar(binding.toolbar)

    val navController = findNavController(R.id.myNavHostFragment)
    val appBarConfiguration = AppBarConfiguration(navController.graph)

    binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
      if (nd.id == R.id.detailFragment) {
        binding.toolbar.visibility = View.GONE
      } else {
        binding.toolbar.visibility = View.VISIBLE
      }
    }
  }
}