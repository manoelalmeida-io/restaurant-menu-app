package com.example.restaurantmenu.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantmenu.databinding.FragmentCartBinding

class CartFragment : Fragment() {

	private lateinit var viewModel: CartViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val binding = FragmentCartBinding.inflate(inflater)
		val viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

		binding.viewModel = viewModel
		binding.lifecycleOwner = this

		binding.cartRecyclerView.adapter = CartItemAdapter()

		return binding.root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
	}
}