package com.example.backend.dto.input

import com.example.backend.model.CheckoutMethod
import com.example.backend.model.PaymentMethod

data class OrderDtoInput (
    val id: Long,
    var fkCheckoutMethod: CheckoutMethod?,
    var fkPaymentMethod: PaymentMethod?,
    var dishes: List<OrderDishDtoInput>?
)