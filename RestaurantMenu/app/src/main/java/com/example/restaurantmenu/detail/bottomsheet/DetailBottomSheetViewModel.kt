package com.example.restaurantmenu.detail.bottomsheet

import android.util.Log
import androidx.lifecycle.*
import com.example.restaurantmenu.database.CartItem
import com.example.restaurantmenu.database.CartItemDao
import com.example.restaurantmenu.network.Dish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailBottomSheetViewModel(
	private val dataSource: CartItemDao, private val dish: Dish) : ViewModel() {

	private val _quantity = MutableLiveData<Int>()
	val quantity: LiveData<Int>
		get() = _quantity

	val total: LiveData<String> = Transformations.map(quantity) {
		"R$%.2f".format(dish.price.times(quantity.value!!))
	}

	init {
		_quantity.value = 1
	}

	fun addItem() {
		viewModelScope.launch {
			insert(dish, quantity.value!!)
		}
	}

	private suspend fun insert(dish: Dish, quantity: Int) {
		withContext(Dispatchers.IO) {
			dataSource.insertAll(CartItem(
				name = dish.name,
				price = dish.price,
				imageUrl = dish.imageUrl,
				quantity = quantity,
				fkDish = dish.id
			))

			Log.i("BottomSheetViewModel", "Item inserted")
		}
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