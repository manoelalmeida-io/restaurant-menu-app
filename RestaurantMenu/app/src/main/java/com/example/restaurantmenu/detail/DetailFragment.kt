package com.example.restaurantmenu.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.restaurantmenu.R
import com.example.restaurantmenu.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		val binding = FragmentDetailBinding.inflate(inflater)
		val arguments = DetailFragmentArgs.fromBundle(requireArguments())

		val viewModelFactory = DetailViewModelFactory(arguments.dishId)
		val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

		binding.viewModel = viewModel
		binding.lifecycleOwner = this

		return binding.root
	}
}