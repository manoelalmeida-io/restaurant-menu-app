package com.example.restaurantmenu.menu

import com.example.restaurantmenu.network.DishFilter
import com.google.android.material.tabs.TabLayout

class MenuTabSelectedListener(private val viewModel: MenuViewModel)
	: TabLayout.OnTabSelectedListener {

	override fun onTabReselected(tab: TabLayout.Tab?) = Unit

	override fun onTabUnselected(tab: TabLayout.Tab?) {
		viewModel.clearDishes()
	}

	override fun onTabSelected(tab: TabLayout.Tab?) {
		when (tab?.position) {
			0 -> viewModel.filterDishes(DishFilter.SHOW_HOT_DRINKS)
			1 -> viewModel.filterDishes(DishFilter.SHOW_COLD_DRINKS)
			2 -> viewModel.filterDishes(DishFilter.SHOW_CAKES)
			3 -> viewModel.filterDishes(DishFilter.SHOW_PASTRY)
			4 -> viewModel.filterDishes(DishFilter.SHOW_SAVORY_FOOD)
			else -> viewModel.filterDishes(null)
		}
	}
}