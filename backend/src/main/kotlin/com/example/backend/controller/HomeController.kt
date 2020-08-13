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
    return populateList(all, uniqueIdentity = 1)
  }

  fun offers(all: List<Dish>): List<DishDto> {
    return populateList(all, uniqueIdentity = 2)
  }

  fun drinkHints(all: List<Dish>): List<DishDto> {
    return populateList(all, uniqueIdentity = 3, categories = *intArrayOf(1, 2))
  }

  fun savoryHints(all: List<Dish>): List<DishDto> {
    return populateList(all, uniqueIdentity = 4, categories = *intArrayOf(5))
  }

  fun populateList(all: List<Dish>,
                   size: Int = 4,
                   uniqueIdentity: Int,
                   vararg categories: Int): List<DishDto> {

    var filteredList = all

    if (categories.isNotEmpty()) {
      filteredList = all.filter { categories.contains(it.fkCategory ?: 0) }
    }

    val random = Random(seed = randomSeed() * uniqueIdentity)
    val randomIndexes = mutableListOf<Int>()

    while (randomIndexes.size < size) {
      val index = random.nextInt(filteredList.size)

      if (randomIndexes.contains(index).not()) {
        randomIndexes.add(index)
      }
    }

    return randomIndexes.map { filteredList[it].toDto() }
  }

  fun randomSeed(): Long {
    val currentDate = LocalDate.now()
    return currentDate.toEpochDay()
  }
}