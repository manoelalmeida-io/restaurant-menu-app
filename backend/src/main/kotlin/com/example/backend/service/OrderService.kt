package com.example.backend.service

import com.example.backend.model.Order
import com.example.backend.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val repository: OrderRepository) {

  fun all() = repository.findAll()
  fun create(order: Order) = repository.save(order)
}