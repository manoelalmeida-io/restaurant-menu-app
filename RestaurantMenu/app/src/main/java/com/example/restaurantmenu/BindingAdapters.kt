package com.example.restaurantmenu

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.restaurantmenu.menu.MenuItemAdapter
import com.example.restaurantmenu.network.Dish

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Dish?>) {
  val adapter = recyclerView.adapter as MenuItemAdapter
  adapter.submitList(data)
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