package com.example.restaurantmenu.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantmenu.databinding.MenuGridItemBinding
import com.example.restaurantmenu.network.Dish

class MenuItemAdapter : ListAdapter<Dish, MenuItemViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        return MenuItemViewHolder(MenuGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val dish = getItem(position)
        holder.bind(dish)
    }
}

class MenuItemViewHolder(private var binding: MenuGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dish: Dish) {
        binding.dish = dish
        binding.executePendingBindings()
    }
}