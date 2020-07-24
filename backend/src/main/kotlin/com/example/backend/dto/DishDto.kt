package com.example.backend.dto

data class DishDto(
    var id: Long,
    var name: String,
    var price: Double,
    var imageUrl: String?,
    var fkCategory: Int?
)