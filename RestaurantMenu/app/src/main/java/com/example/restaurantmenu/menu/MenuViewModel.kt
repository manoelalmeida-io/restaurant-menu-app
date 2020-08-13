package com.example.restaurantmenu.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantmenu.network.Dish
import com.example.restaurantmenu.network.DishFilter
import com.example.restaurantmenu.network.RestaurantApi
import kotlinx.coroutines.*

class MenuViewModel : ViewModel() {

	enum class ApiStatus { LOADING, ERROR, DONE }

	private val _status = MutableLiveData<ApiStatus>()
	val status: LiveData<ApiStatus>
		get() = _status

	private val _dishes = MutableLiveData<List<Dish>>()
	val dishes: LiveData<List<Dish>>
		get() = _dishes

	private val _navigationToDetail = MutableLiveData<Long>()
	val navigationToDetail: LiveData<Long>
		get() = _navigationToDetail

	private var job: Job? = null

	init {
		_dishes.value = ArrayList()

		getDishes(DishFilter.SHOW_HOT_DRINKS)
	}

	fun getDishes(filter: DishFilter?) {
		if (_status.value == ApiStatus.LOADING) {
			job?.cancel()
		}

		job = viewModelScope.launch {
			val getDishesDeferred = RestaurantApi.retrofitService.getDishesAsync(filter?.value)

			try {
				_status.value = ApiStatus.LOADING
				_dishes.value = ArrayList()
				val result = getDishesDeferred.await()

				if (result.isNotEmpty()) {
					_dishes.value = result
					_status.value = ApiStatus.DONE
				}

			} catch (t: Throwable) {
				t.printStackTrace()
				_status.value = ApiStatus.ERROR
				_dishes.value = ArrayList()
			}
		}
	}

	fun filterDishes(filter: DishFilter?) {
		getDishes(filter)
	}

	fun clearDishes() {
		_dishes.value = ArrayList()
	}

	fun displayDishDetails(dish: Dish) {
		_navigationToDetail.value = dish.id
	}

	fun navigationToDetailComplete() {
		_navigationToDetail.value = null
	}
}