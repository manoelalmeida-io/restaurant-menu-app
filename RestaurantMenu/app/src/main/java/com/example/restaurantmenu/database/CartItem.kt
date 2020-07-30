package com.example.restaurantmenu.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(
	@PrimaryKey(autoGenerate = true)
	var id: Long = 0L,
	var name: String,
	var price: Double,
	var quantity: Int,
	var datetime: Long = System.currentTimeMillis(),
	@ColumnInfo(name = "image_url") var imageUrl: String,
	@ColumnInfo(name = "dish_id") var fkDish: Long
)