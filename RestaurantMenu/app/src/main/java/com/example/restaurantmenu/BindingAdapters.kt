package com.example.restaurantmenu

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.restaurantmenu.cart.CartItemAdapter
import com.example.restaurantmenu.database.CartItem
import com.example.restaurantmenu.menu.MenuItemAdapter
import com.example.restaurantmenu.menu.MenuViewModel
import com.example.restaurantmenu.network.Dish

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Dish?>) {
  val adapter = recyclerView.adapter as MenuItemAdapter
  adapter.submitList(data)
}

@BindingAdapter("listCart")
fun bindCartRecyclerView(recyclerView: RecyclerView, data: List<CartItem?>?) {
  val adapter = recyclerView.adapter as CartItemAdapter
  adapter.submitList(data)

  if (adapter.currentList == data) adapter.notifyDataSetChanged()
}

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
  imgUrl?.let {
    val imgUri = it.toUri().buildUpon().scheme("http").build()
    Glide.with(imgView.context)
        .load(imgUri)
        .apply(RequestOptions()
            .error(R.drawable.ic_broken_image)
        )
        .into(imgView)
  }
}

@BindingAdapter("apiStatus")
fun bindProgressBar(progressBar: ProgressBar, status: MenuViewModel.ApiStatus) {
  if (status == MenuViewModel.ApiStatus.LOADING) {
    progressBar.visibility = View.VISIBLE
  } else {
    progressBar.visibility = View.GONE
  }
}

@BindingAdapter("monetaryText")
fun bindMonetaryTextView(textView: TextView, value: Double) {
  textView.text = "R$%.2f".format(value)
}