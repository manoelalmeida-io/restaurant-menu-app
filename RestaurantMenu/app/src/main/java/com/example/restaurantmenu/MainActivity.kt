package com.example.restaurantmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.restaurantmenu.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    setSupportActionBar(binding.toolbar)

    val navController = findNavController(R.id.myNavHostFragment)
    val appBarConfiguration = AppBarConfiguration(binding.bottomNavigation.menu)

    binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    binding.bottomNavigation.setupWithNavController(navController)

    navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->

      val toolbarParams = binding.toolbar.layoutParams as AppBarLayout.LayoutParams

      if (nd.id == R.id.detailFragment) {
        toolbarParams.scrollFlags = 0

        binding.toolbar.title = ""
        binding.bottomNavigation.visibility = View.GONE
      } else {
        toolbarParams.scrollFlags = (SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS)

        binding.bottomNavigation.visibility = View.VISIBLE
      }
    }
  }
}