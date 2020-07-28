package com.example.backend.service

import com.example.backend.dto.input.OrderDtoInput
import com.example.backend.model.Order
import com.example.backend.model.OrderDish
import com.example.backend.model.key.OrderDishKey
import com.example.backend.repository.OrderDishRepository
import com.example.backend.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val repository: OrderRepository,
    private val orderDishRepository: OrderDishRepository) {

  fun all(): List<Order> = repository.findAll()

  fun create(dto: OrderDtoInput): Order {
    val order = Order(
        id = dto.id,
        fkCheckoutMethod = dto.fkCheckoutMethod,
        fkPaymentMethod = dto.fkPaymentMethod,
        dishes = null
    )

    repository.save(order)

    val dishes = dto.dishes?.map {
      OrderDish(
          id = OrderDishKey(
              fkOrder = order.id,
              fkDish = it.id
          ),
          quantity = it.quantity,
          order = null,
          dish = null
      )
    }

    if (dishes.isNullOrEmpty().not()) {
      orderDishRepository.saveAll(dishes!!.asIterable())
    }

    return order
  }
}