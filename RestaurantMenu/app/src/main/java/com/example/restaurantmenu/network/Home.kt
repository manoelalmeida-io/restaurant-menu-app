package com.example.restaurantmenu.network

data class Home(
	val highlights: List<Dish>,
	val offers: List<Dish>,
	val drinkHints: List<Dish>,
	val savoryHints: List<Dish>
)