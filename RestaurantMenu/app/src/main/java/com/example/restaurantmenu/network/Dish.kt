package com.example.restaurantmenu.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dish (
    val id: Long,
    val name: String,
    val price: Double,
    val imageUrl: String
) : Parcelable