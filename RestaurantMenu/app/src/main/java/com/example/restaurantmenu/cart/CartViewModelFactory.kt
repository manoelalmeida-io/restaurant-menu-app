package com.example.restaurantmenu.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantmenu.database.CartItemDao

class CartViewModelFactory(private val dataSource: CartItemDao) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
			return CartViewModel(dataSource) as T
		}

		throw IllegalArgumentException("Unknown ViewModel class")
	}
}