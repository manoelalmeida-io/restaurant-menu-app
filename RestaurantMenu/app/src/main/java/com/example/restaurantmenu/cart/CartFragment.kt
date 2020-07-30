package com.example.restaurantmenu.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantmenu.database.AppDatabase
import com.example.restaurantmenu.databinding.FragmentCartBinding

class CartFragment : Fragment() {

	private lateinit var viewModel: CartViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val binding = FragmentCartBinding.inflate(inflater)

		val application = requireNotNull(this.activity).application
		val dataSource = AppDatabase.getDatabase(application).cartItemDao()

		val viewModelFactory = CartViewModelFactory(dataSource)
		val viewModel = ViewModelProvider(this, viewModelFactory).get(CartViewModel::class.java)

		binding.viewModel = viewModel
		binding.lifecycleOwner = this

		binding.cartRecyclerView.adapter = CartItemAdapter()

		CartItemTouchHelper(viewModel).simpleCallback().attachToRecyclerView(binding.cartRecyclerView)

		return binding.root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
	}
}