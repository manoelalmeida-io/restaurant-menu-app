package com.example.backend.controller

import com.example.backend.dto.DishDto
import com.example.backend.extensions.toDto
import com.example.backend.extensions.toDtoList
import com.example.backend.model.Dish
import com.example.backend.service.DishService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dishes")
class DishController(val service: DishService) {

  @GetMapping
  fun all(@RequestParam category: Int?): ResponseEntity<List<DishDto>> {
    val dishes = service.all(category)

    return ResponseEntity.ok(dishes.toDtoList())
  }

  @GetMapping("/{id}")
  fun find(@PathVariable id: Long): ResponseEntity<DishDto> {
    val dish = service.one(id)

    return ResponseEntity.ok(dish.toDto())
  }
}