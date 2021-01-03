/*
 * Copyright (c) 2020/  10/ 5.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hashim.androidtestplayground.R
import com.hashim.androidtestplayground.viewmodel.ShoppingViewModel
import kotlinx.android.synthetic.main.frament_add_shopping_item.*

class AddShoppingItemFragment() : BaseFragment(R.layout.frament_add_shopping_item) {

    lateinit var hShoppingViewModel: ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hShoppingViewModel = ViewModelProvider(requireActivity())
            .get(ShoppingViewModel::class.java)

        ivShoppingImage.setOnClickListener {
            findNavController().navigate(
                AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToPickImageFragment()
            )
        }


        val hBackPressCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                hShoppingViewModel.hSetImageUrl("")
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(hBackPressCallBack)
    }


}