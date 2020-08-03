package com.example.restaurantmenu.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurantmenu.network.Dish

class HomeViewModel : ViewModel() {

	private val _listHighlights = MutableLiveData<List<Dish?>>()
	val listHighlights: LiveData<List<Dish?>>
		get() = _listHighlights

	init {
		_listHighlights.value = listOf(
			Dish(
				id = 1L,
				name = "adfs",
				price = 12.99,
				imageUrl = ""
			),
			Dish(
				id = 1L,
				name = "adfs",
				price = 12.99,
				imageUrl = ""
			)
		)
	}
}