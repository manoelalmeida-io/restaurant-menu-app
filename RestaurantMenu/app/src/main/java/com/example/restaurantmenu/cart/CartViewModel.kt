package com.example.restaurantmenu.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantmenu.database.CartItem
import com.example.restaurantmenu.database.CartItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private val dataSource: CartItemDao) : ViewModel() {

	val cartItems = dataSource.getAll()

	fun removeItem(position: Int) {
		val cartItem = cartItems.value?.get(position)

		if (cartItem != null) {
			viewModelScope.launch {
				removeItemFromDatabase(cartItem)
			}
		}
	}

	private suspend fun removeItemFromDatabase(cartItem: CartItem) {
		withContext(Dispatchers.IO) {
			dataSource.delete(cartItem)
		}
	}

	fun updateItem(cartItem: CartItem) {
		viewModelScope.launch {
			updateItemFromDatabase(cartItem)
		}
	}

	private suspend fun updateItemFromDatabase(cartItem: CartItem) {
		withContext(Dispatchers.IO) {
			dataSource.update(cartItem)
		}
	}
}