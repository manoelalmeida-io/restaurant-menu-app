package com.example.restaurantmenu.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.restaurantmenu.databinding.FragmentDetailBinding
import com.example.restaurantmenu.menu.MenuItemAdapter
import com.example.restaurantmenu.menu.OnClickListener

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

		binding.addCartButton.setOnClickListener {
			viewModel.openBottomSheet(this.requireActivity().supportFragmentManager)
		}

		binding.suggestionsRecyclerView.adapter = MenuItemAdapter(OnClickListener {
			viewModel.displayDishDetails(it)
		})

		viewModel.navigationToDetail.observe(viewLifecycleOwner, Observer { dishId ->
			if (dishId != null) {
				this.findNavController().navigate(
					DetailFragmentDirections.actionDetailFragmentSelf(dishId)
				)
				viewModel.navigationToDetailComplete()
			}
		})

		return binding.root
	}
}