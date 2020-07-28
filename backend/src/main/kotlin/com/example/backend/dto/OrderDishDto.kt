package com.example.backend.dto

import com.example.backend.model.Dish

data class OrderDishDto(
    val dish: Dish?,
    val quantity: Int
)