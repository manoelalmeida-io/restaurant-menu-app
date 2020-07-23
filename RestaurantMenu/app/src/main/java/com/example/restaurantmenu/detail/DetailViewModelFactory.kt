package com.example.restaurantmenu.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val dishId: Long) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
			return DetailViewModel(dishId) as T
		}

		throw IllegalArgumentException("Unknown ViewModel class")
	}
}