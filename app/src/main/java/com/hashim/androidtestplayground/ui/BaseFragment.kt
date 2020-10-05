/*
 * Copyright (c) 2020/  10/ 5.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hashim.androidtestplayground.viewmodel.ShoppingViewModel

open class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    protected val hShoppViewModel: ShoppingViewModel by viewModels()
}