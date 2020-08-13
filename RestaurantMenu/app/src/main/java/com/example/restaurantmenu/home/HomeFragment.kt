package com.example.restaurantmenu.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

		val listClickListener = OnClickListener {
			viewModel.displayDishDetails(it)
		}

		binding.homeHighlightList.adapter = HomeItemAdapter(listClickListener)
		binding.homeOffersList.adapter = HomeItemAdapter(listClickListener)
		binding.homeDrinksList.adapter = HomeItemAdapter(listClickListener)
		binding.homeSavoryList.adapter = HomeItemAdapter(listClickListener)

		viewModel.status.observe(this.viewLifecycleOwner, Observer {
			when (it) {
				HomeViewModel.ApiStatus.LOADING -> {
					binding.homeContent.visibility = View.GONE
					binding.homeProgressBar.visibility = View.VISIBLE
					binding.apiErrorLayout.root.visibility = View.GONE
				}
				HomeViewModel.ApiStatus.DONE -> {
					binding.homeContent.visibility = View.VISIBLE
					binding.homeProgressBar.visibility = View.GONE
					binding.apiErrorLayout.root.visibility = View.GONE
				}
				else -> {
					binding.homeContent.visibility = View.GONE
					binding.homeProgressBar.visibility = View.GONE
					binding.apiErrorLayout.root.visibility = View.VISIBLE
				}
			}
		})

		viewModel.navigateToDetail.observe(this.viewLifecycleOwner, Observer {
			if (it != null) {
				this.findNavController().navigate(
					HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
				)
				viewModel.navigateToDetailCompleted()
			}
		})

		binding.apiErrorLayout.btnTry.setOnClickListener {
			viewModel.getHome()
		}

		return binding.root
	}
}