package com.example.restaurantmenu.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantmenu.database.CartItem
import com.example.restaurantmenu.databinding.CartListItemBinding

class CartItemAdapter : ListAdapter<CartItem, CartItemViewHolder>(DiffCallback) {

	companion object DiffCallback : DiffUtil.ItemCallback<CartItem>() {
		override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
			return oldItem == newItem
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
		return CartItemViewHolder(CartListItemBinding.inflate(LayoutInflater.from(parent.context)))
	}

	override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
		val cartItem = getItem(position)
		holder.bind(cartItem)
	}
}

class CartItemViewHolder(private val binding: CartListItemBinding) : RecyclerView.ViewHolder(binding.root) {

	fun bind(cartItem: CartItem) {
		binding.item = cartItem
		binding.executePendingBindings()
	}
}