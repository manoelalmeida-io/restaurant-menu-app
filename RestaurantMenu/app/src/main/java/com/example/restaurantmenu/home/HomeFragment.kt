package com.example.restaurantmenu.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantmenu.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

	private lateinit var viewModel: HomeViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val binding = FragmentHomeBinding.inflate(inflater)

		viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

		binding.viewModel = viewModel
		binding.lifecycleOwner = this

		binding.homeHighlightList.adapter = HomeItemAdapter()

		return binding.root
	}
}