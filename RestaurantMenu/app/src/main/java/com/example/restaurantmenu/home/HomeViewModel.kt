package com.example.restaurantmenu.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantmenu.network.Dish
import com.example.restaurantmenu.network.Home
import com.example.restaurantmenu.network.RestaurantApi
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

	enum class ApiStatus { LOADING, ERROR, DONE }

	private val _status = MutableLiveData<ApiStatus>()
	val status: LiveData<ApiStatus>
		get() = _status

	private val _home = MutableLiveData<Home>()
	val home: LiveData<Home>
		get() = _home

	private val _navigateToDetail = MutableLiveData<Long>()
	val navigateToDetail: LiveData<Long>
		get() = _navigateToDetail

	init {
		_home.value = Home(
			highlights = ArrayList(),
			offers = ArrayList(),
			drinkHints = ArrayList(),
			savoryHints = ArrayList()
		)
		getHome()
	}

	fun getHome() {
		viewModelScope.launch {
			val getHomeDeferred = RestaurantApi.retrofitService.getHomeAsync()

			try {
				_status.value = ApiStatus.LOADING
				val result = getHomeDeferred.await()
				_home.value = result
				_status.value = ApiStatus.DONE
			} catch (t: Throwable) {
				t.printStackTrace()
				_status.value = ApiStatus.ERROR
			}
		}
	}

	fun displayDishDetails(dish: Dish) {
		_navigateToDetail.value = dish.id
	}

	fun navigateToDetailCompleted() {
		_navigateToDetail.value = null
	}
}