package com.example.restaurantmenu.menu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.restaurantmenu.databinding.FragmentMenuBinding
import com.example.restaurantmenu.network.DishFilter
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

	companion object {
		private const val KEY_TAB_LAYOUT_POSITION = "key_tab_layout_position"
	}

	private lateinit var binding: FragmentMenuBinding
	private var selectedTab = 0

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentMenuBinding.inflate(inflater)
		val viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

		binding.viewModel = viewModel
		binding.lifecycleOwner = this

		binding.dishesRecyclerView.adapter = MenuItemAdapter(OnClickListener {
			viewModel.displayDishDetails(it)
		})

		binding.menuTabLayout.addOnTabSelectedListener(MenuTabSelectedListener(viewModel))

		viewModel.navigationToDetail.observe(viewLifecycleOwner, Observer { dishId ->
			if (dishId != null) {
				this.findNavController().navigate(
					MenuFragmentDirections.actionMenuFragmentToDetailFragment(dishId)
				)
				viewModel.navigationToDetailComplete()
			}
		})

		viewModel.status.observe(viewLifecycleOwner, Observer { apiStatus ->
			when (apiStatus) {
				MenuViewModel.ApiStatus.LOADING -> {
					binding.apiErrorLayout.root.visibility = View.GONE
					binding.progressIndicator.visibility = View.VISIBLE
				}
				MenuViewModel.ApiStatus.DONE -> {
					binding.apiErrorLayout.root.visibility = View.GONE
					binding.progressIndicator.visibility = View.GONE
				}
				else -> {
					binding.apiErrorLayout.root.visibility = View.VISIBLE
					binding.progressIndicator.visibility = View.GONE
				}
			}
		})

		binding.apiErrorLayout.btnTry.setOnClickListener {
			viewModel.getDishes(DishFilter.SHOW_HOT_DRINKS)
		}

		return binding.root
	}

	override fun onStart() {
		super.onStart()

		binding.menuTabLayout.selectTab(menuTabLayout.getTabAt(selectedTab))
	}

	override fun onStop() {
		super.onStop()

		selectedTab = binding.menuTabLayout.selectedTabPosition
	}

	override fun onViewStateRestored(savedInstanceState: Bundle?) {
		super.onViewStateRestored(savedInstanceState)

		if (savedInstanceState != null) {
			selectedTab = savedInstanceState.getInt(KEY_TAB_LAYOUT_POSITION)
		}
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)

		val selectedTab = selectedTab
		outState.putInt(KEY_TAB_LAYOUT_POSITION, selectedTab)
	}
}