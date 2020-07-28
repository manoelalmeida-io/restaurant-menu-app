package com.example.backend.model

import com.example.backend.model.key.OrderDishKey
import javax.persistence.*

@Entity
data class OrderDish(
    @EmbeddedId
    val id: OrderDishKey,

    @ManyToOne
    @MapsId(value = "fk_user_order")
    @JoinColumn(name = "fk_user_order")
    val order: Order?,

    @ManyToOne
    @MapsId(value = "fk_dish")
    @JoinColumn(name = "fk_dish")
    val dish: Dish?,

    val quantity: Int
)