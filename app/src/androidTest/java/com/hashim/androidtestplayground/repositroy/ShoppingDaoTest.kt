/*
 * Copyright (c) 2020/  8/ 24.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repositroy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.common.truth.Truth
import com.hashim.androidtestplayground.getOrAwaitValue
import com.hashim.androidtestplayground.repository.local.ShoppinDatabase
import com.hashim.androidtestplayground.repository.local.ShoppingDao
import com.hashim.androidtestplayground.repository.local.ShoppingItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/*Live data is synchronours by default which causes the test to fail even with
* run blocking Test  so make it run successfully we have to tell the junit to run
* each test one after another which is done by specifying a rule for junit*/
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    var hInstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var hShoppinDatabase: ShoppinDatabase
    private lateinit var hShoppingDao: ShoppingDao

    @Before
    fun setup() {
        hShoppinDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppinDatabase::class.java,
        )
            .allowMainThreadQueries()
            .build()
        hShoppingDao = hShoppinDatabase.hGetShoppingDao()
    }

    @After
    fun teardown() {
        hShoppinDatabase.close()
    }


    @Test
    fun insert_item_test() = runBlockingTest {
        val hShoppingItem = ShoppingItem(
            "name", 100, 30F, "url", 1
        )
        hShoppingDao.hInsertShoppingItem(hShoppingItem)

        val hDbItemsList = hShoppingDao.hGetAllShoppingItems().getOrAwaitValue()



        Truth.assertThat(hDbItemsList).contains(hShoppingItem)

    }

    @Test
    fun delete_item_test() {
        runBlockingTest {
            val hShoppingItem = ShoppingItem(
                "name", 100, 30F, "url", 1
            )
            hShoppingDao.hInsertShoppingItem(hShoppingItem)

            hShoppingDao.hDeleteShoppingItem(hShoppingItem)

            val hDbItemsList = hShoppingDao.hGetAllShoppingItems().getOrAwaitValue()

            Truth.assertThat(hDbItemsList).doesNotContain(hShoppingItem)
        }
    }

    @Test
    fun total_price_test() {
        runBlockingTest {
            val hShoppingItem1 = ShoppingItem(
                "name", 5, 30.5F, "url", 1
            )
            val hShoppingItem2 = ShoppingItem(
                "name", 10, 40F, "url", 2
            )
            val hShoppingItem3 = ShoppingItem(
                "name", 0, 50F, "url", 3
            )

            hShoppingDao.hInsertShoppingItem(hShoppingItem1)
            hShoppingDao.hInsertShoppingItem(hShoppingItem2)
            hShoppingDao.hInsertShoppingItem(hShoppingItem3)

            val hPrice = hShoppingDao.hGetTotalPriceItems().getOrAwaitValue()

            Truth.assertThat(hPrice).isEqualTo(5 * 30.5F + 10 * 40F + 0 * 50F)
        }
    }
}