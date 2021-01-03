/*
 * Copyright (c) 2021/  1/ 3.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.hashim.androidtestplayground.R
import com.hashim.androidtestplayground.adapters.ImageAdapter
import com.hashim.androidtestplayground.getOrAwaitValue
import com.hashim.androidtestplayground.launchFragmentInHiltContainer
import com.hashim.androidtestplayground.repository.FakeRepoAndroidTest
import com.hashim.androidtestplayground.viewmodel.ShoppingViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PickImageFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var hHiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var hFragmentFactory: ShoppingFragmentFactory

    @Before
    fun setup() {
        hHiltRule.inject()
    }


    @Test
    fun hClickImagePopbackStackAndSetImageUrl() {
        val navController = mock(NavController::class.java)
        val imageUrl = "TEST"
        val testViewModel = ShoppingViewModel(FakeRepoAndroidTest())
        launchFragmentInHiltContainer<PickImageFragment>(fragmentFactory = hFragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
            hImageAdapter.images = listOf(imageUrl)
            hShoppingViewModel = testViewModel
        }

        onView(withId(R.id.rvImages)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageViewHolder>(
                0,
                click()
            )
        )

        verify(navController).popBackStack()
        assertThat(testViewModel.hImageUrlLD.getOrAwaitValue()).isEqualTo(imageUrl)
    }
}