package com.example.restaurantmenu.detail

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.example.restaurantmenu.detail.bottomsheet.DetailBottomSheet
import com.example.restaurantmenu.menu.MenuViewModel
import com.example.restaurantmenu.network.Dish
import com.example.restaurantmenu.network.RestaurantApi
import kotlinx.coroutines.launch

class DetailViewModel(private val dishId: Long) : ViewModel() {

	private val _selectedDish = MutableLiveData<Dish>()
	val selectedDish: LiveData<Dish>
		get() = _selectedDish

	private val _suggestions = MutableLiveData<List<Dish>>()
	val suggestions: LiveData<List<Dish>>
		get() = _suggestions

	private val _navigationToDetail = MutableLiveData<Long>()
	val navigationToDetail: LiveData<Long>
		get() = _navigationToDetail

	val priceString: LiveData<String> = Transformations.map(selectedDish) { dish ->
		"R$%.2f".format(dish.price)
	}

	init {
		_suggestions.value = ArrayList()

		getDish()
		getSuggestions()
	}

	private fun getDish() {
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

	private fun getSuggestions() {
		viewModelScope.launch {
			val getSuggestionsDeferred = RestaurantApi.retrofitService.getSuggestionsAsync(dishId)

			try {
				val result = getSuggestionsDeferred.await()
				Log.i("DetailViewModel", "Result: $result")

				if (result.isNotEmpty()) {
					_suggestions.value = result
				}
			} catch (t: Throwable) {
				t.printStackTrace()
			}
		}
	}

	fun openBottomSheet(supportFragmentManager: FragmentManager) {
		val dish = selectedDish.value

		if (dish != null) {
			val bottomSheet = DetailBottomSheet(dish)
			bottomSheet.show(supportFragmentManager, "TAG")
		}
	}

	fun displayDishDetails(dish: Dish) {
		_navigationToDetail.value = dish.id
	}

	fun navigationToDetailComplete() {
		_navigationToDetail.value = null
	}
}