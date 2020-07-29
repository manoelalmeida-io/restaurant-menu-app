package com.example.restaurantmenu.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurantmenu.database.CartItem

class CartViewModel : ViewModel() {

	private val _cartItems = MutableLiveData<List<CartItem?>>()
	val cartItems: LiveData<List<CartItem?>>
		get() = _cartItems

	init {
		_cartItems.value = listOf(
			CartItem(
				name = "Sonho com Topping de Caramelo",
				price = 19.90,
				imageUrl = "http://192.168.1.34:8080/images/q1.png",
				quantity = 1,
				datetime = System.currentTimeMillis()
		))
	}
}