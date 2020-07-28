package com.example.backend.model

import javax.persistence.*

@Entity
@Table(name = "user_order")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var fkCheckoutMethod: CheckoutMethod?,
    var fkPaymentMethod: PaymentMethod?,

    @OneToMany(mappedBy = "order")
    var dishes: List<OrderDish>?
)