/*
 * Copyright (c) 2021/  1/ 3.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.hashim.androidtestplayground.adapters.ImageAdapter
import javax.inject.Inject

class ShoppingFragmentFactory @Inject constructor(
    private val hImageAdapter: ImageAdapter
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PickImageFragment::class.java.name -> PickImageFragment(hImageAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}
