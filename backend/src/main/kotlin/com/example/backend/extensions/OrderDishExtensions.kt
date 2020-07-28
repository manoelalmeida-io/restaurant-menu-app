package com.example.backend.extensions

import com.example.backend.dto.OrderDishDto
import com.example.backend.model.OrderDish

fun OrderDish.toDto(): OrderDishDto {
  return OrderDishDto(
      dish = this.dish,
      quantity = this.quantity
  )
}

fun List<OrderDish>.toDtoList(): List<OrderDishDto> {
  return this.map { it.toDto() }
}