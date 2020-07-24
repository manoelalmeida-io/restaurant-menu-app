package com.example.restaurantmenu.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.restaurantmenu.databinding.FragmentMenuBinding
import com.google.android.material.tabs.TabLayout

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMenuBinding.inflate(inflater)
        val viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.dishesRecyclerView.adapter = MenuItemAdapter(OnClickListener {
            viewModel.displayDishDetails(it)
        })

        binding.menuTabLayout.addOnTabSelectedListener(MenuTabSelectedListener(viewModel))

        viewModel.navigationToDetail.observe(viewLifecycleOwner, Observer { dishId ->
            if (dishId != null) {
                this.findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToDetailFragment(dishId))
                viewModel.navigationToDetailComplete()
            }
        })

        return binding.root
    }
}