package com.example.backend.dto

import com.example.backend.model.CheckoutMethod
import com.example.backend.model.PaymentMethod

data class OrderDto(
    val id: Long,
    var fkCheckoutMethod: CheckoutMethod?,
    var fkPaymentMethod: PaymentMethod?,
    var dishes: List<OrderDishDto>?
)