package com.example.backend.dto

data class HomeDto(
    val highlights: List<DishDto>,
    val offers: List<DishDto>,
    val drinkHints: List<DishDto>,
    val savoryHints: List<DishDto>
)