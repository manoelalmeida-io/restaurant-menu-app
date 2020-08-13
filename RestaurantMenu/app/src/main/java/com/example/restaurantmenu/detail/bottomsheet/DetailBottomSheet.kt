package com.example.restaurantmenu.detail.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantmenu.database.AppDatabase
import com.example.restaurantmenu.databinding.DetailBottomSheetBinding
import com.example.restaurantmenu.network.Dish
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailBottomSheet : BottomSheetDialogFragment() {

	companion object {

		fun newInstance(dish: Dish): DetailBottomSheet {
			val args = Bundle()
			args.putParcelable("dish", dish)
			val detailBottomSheet = DetailBottomSheet()
			detailBottomSheet.arguments = args
			return detailBottomSheet
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val binding = DetailBottomSheetBinding.inflate(inflater)

		val application = requireNotNull(this.activity).application
		val dataSource = AppDatabase.getDatabase(application).cartItemDao()

		val viewModelFactory = DetailBottomSheetViewModelFactory(dataSource, arguments?.getParcelable("dish")!!)
		val viewModel = ViewModelProvider(this, viewModelFactory)
				.get(DetailBottomSheetViewModel::class.java)

		binding.viewModel = viewModel
		binding.lifecycleOwner = this

		binding.btnAddUnit.setOnClickListener {
			viewModel.addUnit()
		}

		binding.btnRemoveUnit.setOnClickListener {
			viewModel.removeUnit()
		}

		binding.btnAddToCart.setOnClickListener {
			viewModel.addItem()
			this.dismiss()
		}

		binding.btnClose.setOnClickListener {
			this.dismiss()
		}

		return binding.root
	}
}