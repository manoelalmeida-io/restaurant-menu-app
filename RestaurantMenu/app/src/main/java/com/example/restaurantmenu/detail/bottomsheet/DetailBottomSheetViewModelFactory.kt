package com.example.restaurantmenu.detail.bottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantmenu.network.Dish
import java.lang.IllegalArgumentException

class DetailBottomSheetViewModelFactory(private val dish: Dish) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(DetailBottomSheetViewModel::class.java)) {
			return DetailBottomSheetViewModel(dish) as T
		}

		throw IllegalArgumentException("Unknown ViewModel class")
	}
}