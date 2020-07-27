package com.example.backend.controller

import com.example.backend.model.Order
import com.example.backend.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(val service: OrderService) {

  @GetMapping
  fun all(): ResponseEntity<List<Order>> {
    val orders = service.all()

    return ResponseEntity.ok(orders)
  }

  @PostMapping
  fun create(@RequestBody order: Order): ResponseEntity<Order> {
    val created = service.create(order)

    return ResponseEntity.status(HttpStatus.CREATED).body(created)
  }
}