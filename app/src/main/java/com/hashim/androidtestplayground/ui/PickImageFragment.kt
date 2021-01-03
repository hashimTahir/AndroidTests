/*
 * Copyright (c) 2020/  10/ 5.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hashim.androidtestplayground.R
import com.hashim.androidtestplayground.adapters.ImageAdapter
import com.hashim.androidtestplayground.viewmodel.ShoppingViewModel
import kotlinx.android.synthetic.main.frament_pick_image.*
import javax.inject.Inject

class PickImageFragment @Inject constructor(
     val hImageAdapter: ImageAdapter
) : BaseFragment(R.layout.frament_pick_image) {
    lateinit var hShoppingViewModel: ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hShoppingViewModel = ViewModelProvider(requireActivity())
            .get(ShoppingViewModel::class.java)


        hImageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            hShoppingViewModel.hSetImageUrl(it)
        }

    }

    private fun hSetupRecyclerView() {
        rvImages.apply {
            adapter = hImageAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }

}