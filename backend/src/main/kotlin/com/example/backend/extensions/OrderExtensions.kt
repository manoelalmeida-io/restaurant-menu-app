package com.example.backend.extensions

import com.example.backend.dto.OrderDto
import com.example.backend.model.Order

fun Order.toDto(): OrderDto {
  return OrderDto(
      id = this.id,
      fkCheckoutMethod = this.fkCheckoutMethod,
      fkPaymentMethod = this.fkPaymentMethod,
      dishes = this.dishes?.toDtoList()
  )
}

fun List<Order>.toDtoList(): List<OrderDto> {
  return this.map { it.toDto() }
}