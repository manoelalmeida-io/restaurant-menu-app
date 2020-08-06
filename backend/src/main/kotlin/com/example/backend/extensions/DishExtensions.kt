package com.example.backend.extensions

import com.example.backend.dto.DishDto
import com.example.backend.model.Dish

fun Dish.toDto(): DishDto {
  val hostname = "192.168.0.100"
  val port = "8080"
  val url = "http://$hostname:$port/images/${this.imageFilename}"

  return DishDto(
      id = this.id,
      name = this.name,
      price = this.price,
      imageUrl = if (this.imageFilename.isNullOrBlank().not()) url else null,
      fkCategory = this.fkCategory
  )
}

fun List<Dish>.toDtoList(): List<DishDto> {
  return this.map { it.toDto() }
}