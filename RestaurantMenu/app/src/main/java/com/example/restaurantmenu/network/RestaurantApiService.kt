package com.example.restaurantmenu.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "http://192.168.1.34:8080/"

enum class DishFilter(val value: Int) {
	SHOW_HOT_DRINKS(1),
	SHOW_COLD_DRINKS(2),
	SHOW_CAKES(3),
	SHOW_PASTRY(4),
	SHOW_SAVORY_FOOD(5)
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
	  .build()

private val retrofit = Retrofit.Builder()
	  .addConverterFactory(MoshiConverterFactory.create(moshi))
	  .addCallAdapterFactory(CoroutineCallAdapterFactory())
	  .baseUrl(BASE_URL)
	  .build()

interface RestaurantApiService {
	@GET("dishes")
	fun getDishesAsync(@Query("category") type: Int?): Deferred<List<Dish>>

	@GET("dishes/{id}")
	fun getDishAsync(@Path("id") id: Long): Deferred<Dish>
}

object RestaurantApi {
	val retrofitService: RestaurantApiService by lazy {
		retrofit.create(RestaurantApiService::class.java)
	}
}