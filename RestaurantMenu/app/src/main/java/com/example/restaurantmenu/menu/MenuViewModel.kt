package com.example.restaurantmenu.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurantmenu.network.Dish

class MenuViewModel : ViewModel() {

    private val _dishes = MutableLiveData<List<Dish>>()
    val dishes: LiveData<List<Dish>>
        get() = _dishes

    init {
        val d1 = Dish(
            id = 1,
            name = "example",
            price = 18.90,
            imageUrl = "http://192.168.1.34:8080/images/m1.png"
        )

        _dishes.value = listOf(d1)
    }
}