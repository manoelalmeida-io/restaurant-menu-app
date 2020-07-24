package com.example.backend.service

import com.example.backend.exception.ResourceNotFoundException
import com.example.backend.model.Dish
import com.example.backend.repository.DishRepository
import org.springframework.stereotype.Service

@Service
class DishService(private val repository: DishRepository) {

  fun all(category: Int?): List<Dish> {
    category?.let {
      return repository.findByFkCategory(it)
    }

    return repository.findAll()
  }

  fun one(id: Long): Dish {
    val dish = repository.findById(id)

    return dish.orElseThrow { ResourceNotFoundException("Could not find a dish with id '$id'") }
  }
}