/*
 * Copyright (c) 2020/  10/ 5.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.hashim.androidtestplayground.R
import com.hashim.androidtestplayground.viewmodel.ShoppingViewModel

class AddShoppingItemFragment() : BaseFragment(R.layout.frament_add_shopping_item) {

    lateinit var hShoppingViewModel: ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hShoppingViewModel = ViewModelProvider(requireActivity())
            .get(ShoppingViewModel::class.java)

    }


}