package com.example.backend.repository

import com.example.backend.model.Dish
import org.springframework.data.jpa.repository.JpaRepository

interface DishRepository: JpaRepository<Dish, Long>