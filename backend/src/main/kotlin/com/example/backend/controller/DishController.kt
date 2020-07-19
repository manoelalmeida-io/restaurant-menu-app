package com.example.backend.controller

import com.example.backend.model.Dish
import com.example.backend.service.DishService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dishes")
class DishController(val service: DishService) {

  @GetMapping
  fun all(): ResponseEntity<List<Dish>> {
    val dishes = service.all()

    return ResponseEntity.ok(dishes)
  }

  @GetMapping("/{id}")
  fun find(@PathVariable id: Long): ResponseEntity<Dish> {
    val dish = service.one(id)

    return ResponseEntity.ok(dish)
  }
}