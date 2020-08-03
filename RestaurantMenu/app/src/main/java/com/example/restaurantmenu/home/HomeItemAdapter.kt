package com.example.restaurantmenu.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantmenu.databinding.HomeListItemBinding
import com.example.restaurantmenu.network.Dish

class HomeItemAdapter : ListAdapter<Dish, HomeItemViewHolder>(DiffCallback) {

	companion object DiffCallback : DiffUtil.ItemCallback<Dish>() {
		override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
			return oldItem == newItem
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
		return HomeItemViewHolder(HomeListItemBinding.inflate(LayoutInflater.from(parent.context)))
	}

	override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
		val dish = getItem(position)
		holder.bind(dish)
	}
}

class HomeItemViewHolder(private val binding: HomeListItemBinding) : RecyclerView.ViewHolder(binding.root) {

	fun bind(dish: Dish) {
		binding.dish = dish
		binding.executePendingBindings()
	}
}