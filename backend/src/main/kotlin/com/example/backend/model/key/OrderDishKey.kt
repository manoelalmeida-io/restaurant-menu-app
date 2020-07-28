package com.example.backend.model.key

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class OrderDishKey(
    @Column(name = "fk_user_order")
    val fkOrder: Long,
    @Column(name = "fk_dish")
    val fkDish: Long
) : Serializable