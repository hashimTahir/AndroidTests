/*
 * Copyright (c) 2021/  1/ 3.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.pressBack
import androidx.test.filters.MediumTest
import com.hashim.androidtestplayground.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddShoppingItemFragmentTest {

    @get:Rule
    var hHiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hHiltRule.inject()
    }


    @Test
    fun clickAddShoppingItemButton_navigateToAddShoppingItemFragment() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()

        Mockito.verify(navController).popBackStack()
    }
}