package com.example.restaurantmenu.database

data class CartItem(
	var id: Long = 0L,
	var name: String,
	var price: Double,
	var imageUrl: String,
	var quantity: Int,
	var datetime: Long = System.currentTimeMillis()
)