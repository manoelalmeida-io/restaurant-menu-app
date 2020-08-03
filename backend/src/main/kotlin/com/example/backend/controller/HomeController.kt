package com.example.backend.controller

import com.example.backend.dto.DishDto
import com.example.backend.dto.HomeDto
import com.example.backend.extensions.toDto
import com.example.backend.model.Dish
import com.example.backend.service.DishService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import kotlin.random.Random

@RestController
@RequestMapping("/home")
class HomeController(private val dishService: DishService) {

  @GetMapping
  fun home(): ResponseEntity<HomeDto> {
    val allDishes = dishService.all()

    return ResponseEntity.ok(HomeDto(
        highlights = highlights(allDishes),
        offers = offers(allDishes),
        drinkHints = drinkHints(allDishes),
        savoryHints = savoryHints(allDishes)
    ))
  }

  fun highlights(all: List<Dish>): List<DishDto> {
    val random = Random(seed = randomSeed())
    val randomIndexes = List(4) { random.nextInt(all.size) }

    return randomIndexes.map { all[it].toDto() }
  }

  fun offers(all: List<Dish>): List<DishDto> {
    val random = Random(seed = randomSeed() * 2)
    val randomIndexes = List(4) { random.nextInt(all.size) }

    return randomIndexes.map { all[it].toDto() }
  }

  fun drinkHints(all: List<Dish>): List<DishDto> {
    val filteredList = all.filter { listOf(1, 2).contains(it.fkCategory) }
    val random = Random(seed = randomSeed() * 3)
    val randomIndexes = List(4) { random.nextInt(filteredList.size) }

    return randomIndexes.map { filteredList[it].toDto() }
  }

  fun savoryHints(all: List<Dish>): List<DishDto> {
    val filteredList = all.filter { it.fkCategory == 5 }
    val random = Random(seed = randomSeed() * 4)
    val randomIndexes = List(4) { random.nextInt(filteredList.size) }

    return randomIndexes.map { filteredList[it].toDto() }
  }

  fun randomSeed(): Long {
    val currentDate = LocalDate.now()
    return currentDate.toEpochDay()
  }
}