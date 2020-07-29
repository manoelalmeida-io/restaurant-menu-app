package com.example.restaurantmenu.detail.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.restaurantmenu.network.Dish

class DetailBottomSheetViewModel(private val dish: Dish) : ViewModel() {

	private val _quantity = MutableLiveData<Int>()
	val quantity: LiveData<Int>
		get() = _quantity

	val total: LiveData<String> = Transformations.map(quantity) {
		"R$%.2f".format(dish.price.times(quantity.value!!))
	}

	init {
		_quantity.value = 1
	}

	fun addUnit() {
		val currentValue = _quantity.value!!
		_quantity.value = currentValue + 1
	}

	fun removeUnit() {
		val currentValue = _quantity.value!!

		if (currentValue > 1) {
			_quantity.value = currentValue - 1
		}
	}
}