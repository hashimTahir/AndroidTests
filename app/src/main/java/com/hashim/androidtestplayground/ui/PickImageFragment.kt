/*
 * Copyright (c) 2020/  10/ 5.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hashim.androidtestplayground.R
import com.hashim.androidtestplayground.viewmodel.ShoppingViewModel

class PickImageFragment : BaseFragment(R.layout.frament_pick_image) {
    lateinit var hShoppingViewModel: ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hShoppingViewModel = ViewModelProvider(requireActivity())
            .get(ShoppingViewModel::class.java)

    }


}