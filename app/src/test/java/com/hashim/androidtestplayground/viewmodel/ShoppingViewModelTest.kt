/*
 * Copyright (c) 2020/  10/ 7.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.hashim.androidtestplayground.MainCouroutinRule
import com.hashim.androidtestplayground.getOrAwaitValueTest
import com.hashim.androidtestplayground.other.Constants
import com.hashim.androidtestplayground.other.Status
import com.hashim.androidtestplayground.repository.FakeRepoTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest {
    @get:Rule
    var hInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hMainCouroutinRule = MainCouroutinRule()
    private lateinit var hShoppingViewModel: ShoppingViewModel


    @Before
    fun setup() {
        hShoppingViewModel = ShoppingViewModel(FakeRepoTest())
    }

    @Test
    fun `insert shopping item with empty field, returns error`() {
        hShoppingViewModel.hInsertShoppingItem("name", "", "3.0")

        val value = hShoppingViewModel.hInsertShoppingItemStatusLD.getOrAwaitValueTest()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with long name, returns error`() {
        val string = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        hShoppingViewModel.hInsertShoppingItem(string, "5", "3.0")

        val value = hShoppingViewModel.hInsertShoppingItemStatusLD.getOrAwaitValueTest()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with long price, returns error`() {
        val string = buildString {
            for (i in 1..Constants.MAX_PRICE_LENGTH + 1) {
                append(1)
            }
        }
        hShoppingViewModel.hInsertShoppingItem("name", "5", string)

        val value = hShoppingViewModel.hInsertShoppingItemStatusLD.getOrAwaitValueTest()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with high amount, returns error`() {

        hShoppingViewModel.hInsertShoppingItem("name", "99999999999999999999999", "3.0")

        val value = hShoppingViewModel.hInsertShoppingItemStatusLD.getOrAwaitValueTest()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input, returns success`() {

        hShoppingViewModel.hInsertShoppingItem("name", "5", "3.0")

        val value = hShoppingViewModel.hInsertShoppingItemStatusLD.getOrAwaitValueTest()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}