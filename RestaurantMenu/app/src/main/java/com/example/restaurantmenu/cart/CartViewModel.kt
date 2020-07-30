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

	private val _cartItems = MutableLiveData<List<CartItem?>>()
	val cartItems: LiveData<List<CartItem?>>
		get() = _cartItems

	init {
		_cartItems.value = ArrayList()
		getItems()
	}

	private fun getItems() {
		viewModelScope.launch {
			_cartItems.value = getAllCartItems()
		}
	}

	private suspend fun getAllCartItems(): List<CartItem> {
		return withContext(Dispatchers.IO) {
			dataSource.getAll()
		}
	}
}