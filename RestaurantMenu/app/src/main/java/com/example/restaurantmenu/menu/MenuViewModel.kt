package com.example.restaurantmenu.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantmenu.network.Dish
import com.example.restaurantmenu.network.RestaurantApi
import kotlinx.coroutines.*

class MenuViewModel : ViewModel() {

	private val _dishes = MutableLiveData<List<Dish>>()
	val dishes: LiveData<List<Dish>>
		get() = _dishes

	private val _navigationToDetail = MutableLiveData<Long>()
	val navigationToDetail: LiveData<Long>
		get() = _navigationToDetail

	init {
		_dishes.value = ArrayList()

		getDishes()
	}

	private fun getDishes() {
		viewModelScope.launch {
			val getDishesDeferred = RestaurantApi.retrofitService.getDishesAsync()

			try {
				val result = getDishesDeferred.await()

				if (result.isNotEmpty()) {
					_dishes.value = result
				}

			} catch (t: Throwable) {
				t.printStackTrace()
				_dishes.value = ArrayList()
			}
		}
	}

	fun displayDishDetails(dish: Dish) {
		_navigationToDetail.value = dish.id
	}

	fun navigationToDetailComplete() {
		_navigationToDetail.value = null
	}
}