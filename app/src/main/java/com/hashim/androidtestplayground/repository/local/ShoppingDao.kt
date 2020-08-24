/*
 * Copyright (c) 2020/  8/ 24.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun hInsertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun hDeleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("select * from ShoppingItems")
    fun hGetAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("select Sum(hPrice*hAmount) from shoppingitems")
    fun hGetTotalPriceItems(): LiveData<Float>

}