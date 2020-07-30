package com.example.restaurantmenu.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantmenu.database.CartItem
import com.example.restaurantmenu.databinding.CartListItemBinding

class CartItemAdapter(private val onAdd: OnClickListener, private val onRemove: OnClickListener)
	: ListAdapter<CartItem, CartItemViewHolder>(DiffCallback) {

	companion object DiffCallback : DiffUtil.ItemCallback<CartItem>() {
		override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
			Log.i("DiffUtil", "oldItem: $oldItem, newItem: $newItem")
			return oldItem == newItem
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
		return CartItemViewHolder(CartListItemBinding.inflate(LayoutInflater.from(parent.context)))
	}

	override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
		val cartItem = getItem(position)
		holder.bind(cartItem, onAdd, onRemove)
	}
}

class CartItemViewHolder(private val binding: CartListItemBinding) : RecyclerView.ViewHolder(binding.root) {

	fun bind(cartItem: CartItem, onAdd: OnClickListener, onRemove: OnClickListener) {
		binding.item = cartItem
		binding.btnAddUnit.setOnClickListener {
			onAdd.onClick(cartItem)
		}
		binding.btnRemoveUnit.setOnClickListener {
			onRemove.onClick(cartItem)
		}
		binding.executePendingBindings()
	}
}

class OnClickListener(private val clickListener: (cartItem: CartItem) -> Unit) {
	fun onClick(cartItem: CartItem) = clickListener(cartItem)
}