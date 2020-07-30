package com.example.restaurantmenu.database

import androidx.room.*

@Dao
interface CartItemDao {

	@Query("SELECT * FROM cart_item")
	fun getAll(): List<CartItem>

	@Insert
	fun insertAll(vararg cartItems: CartItem)

	@Update
	fun update(cartItem: CartItem)

	@Delete
	fun delete(cartItem: CartItem)
}