package com.example.restaurantmenu.detail

import androidx.lifecycle.*
import com.example.restaurantmenu.network.Dish
import com.example.restaurantmenu.network.RestaurantApi
import kotlinx.coroutines.launch

class DetailViewModel(private val dishId: Long) : ViewModel() {

	private val _selectedDish = MutableLiveData<Dish>()
	val selectedDish: LiveData<Dish>
		get() = _selectedDish

	val priceString = Transformations.map(selectedDish) { dish ->
		"R$%.2f".format(dish.price)
	}

	init {
		getDish()
	}

	fun getDish() {
		viewModelScope.launch {
			val getDishDeferred = RestaurantApi.retrofitService.getDishAsync(dishId)

			try {
				val result = getDishDeferred.await()
				_selectedDish.value = result

			} catch (t: Throwable) {
				t.printStackTrace()
			}
		}
	}
}