package com.example.backend.controller

import com.example.backend.dto.OrderDto
import com.example.backend.dto.input.OrderDtoInput
import com.example.backend.extensions.toDtoList
import com.example.backend.model.Order
import com.example.backend.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(val service: OrderService) {

  @GetMapping
  fun all(): ResponseEntity<List<OrderDto>> {
    val orders = service.all()

    return ResponseEntity.ok(orders.toDtoList())
  }

  @PostMapping
  fun create(@RequestBody orderInput: OrderDtoInput): ResponseEntity<Order> {
    val created = service.create(orderInput)

    return ResponseEntity.status(HttpStatus.CREATED).body(created)
  }
}