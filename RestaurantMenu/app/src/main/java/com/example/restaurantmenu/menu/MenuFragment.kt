package com.example.restaurantmenu.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantmenu.databinding.FragmentMenuBinding

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
        binding.dishesRecyclerView.adapter = MenuItemAdapter()

//        viewModel.dishes.observe(this, Observer {
//
//        })

        return binding.root
    }
}