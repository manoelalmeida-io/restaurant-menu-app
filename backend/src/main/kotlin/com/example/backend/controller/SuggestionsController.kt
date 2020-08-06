package com.example.backend.controller

import com.example.backend.dto.DishDto
import com.example.backend.extensions.toDto
import com.example.backend.model.Dish
import com.example.backend.service.DishService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import kotlin.random.Random

@RestController
@RequestMapping("/suggestions")
class SuggestionsController(private val dishService: DishService) {

  @GetMapping("/{dishId}")
  fun suggestions(@PathVariable dishId: Long): ResponseEntity<List<DishDto>> {
    val allDishes = dishService.all()

    return ResponseEntity.ok(randomSuggestions(allDishes, dishId))
  }

  fun randomSuggestions(all: List<Dish>, id: Long): List<DishDto> {
    val random = Random(seed = randomSeed(id))
    val randomIndexes = List(6) { random.nextInt(all.size) }

    return randomIndexes.map { all[it].toDto() }
  }

  fun randomSeed(id: Long): Long {
    val currentDate = LocalDate.now()
    return currentDate.toEpochDay() / id
  }
}