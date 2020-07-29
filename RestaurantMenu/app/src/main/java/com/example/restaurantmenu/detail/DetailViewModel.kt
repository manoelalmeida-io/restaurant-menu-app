package com.example.restaurantmenu.detail

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.example.restaurantmenu.detail.bottomsheet.DetailBottomSheet
import com.example.restaurantmenu.network.Dish
import com.example.restaurantmenu.network.RestaurantApi
import kotlinx.coroutines.launch

class DetailViewModel(private val dishId: Long) : ViewModel() {

	private val _selectedDish = MutableLiveData<Dish>()
	val selectedDish: LiveData<Dish>
		get() = _selectedDish

	val priceString: LiveData<String> = Transformations.map(selectedDish) { dish ->
		"R$%.2f".format(dish.price)
	}

	init {
		getDish()
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

	fun openBottomSheet(supportFragmentManager: FragmentManager) {
		val dish = selectedDish.value

		if (dish != null) {
			val bottomSheet = DetailBottomSheet(dish)
			bottomSheet.show(supportFragmentManager, "TAG")
		}
	}
}