package com.example.backend.repository

import com.example.backend.model.OrderDish
import com.example.backend.model.key.OrderDishKey
import org.springframework.data.jpa.repository.JpaRepository

interface OrderDishRepository : JpaRepository<OrderDish, OrderDishKey>