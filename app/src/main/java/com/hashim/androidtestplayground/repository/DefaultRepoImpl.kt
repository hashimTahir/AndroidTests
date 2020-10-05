/*
 * Copyright (c) 2020/  8/ 28.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repository

import androidx.lifecycle.LiveData
import com.hashim.androidtestplayground.other.Resource
import com.hashim.androidtestplayground.repository.local.ShoppingItem
import com.hashim.androidtestplayground.repository.remote.models.ImageResponse
import retrofit2.Response

interface DefaultRepoImpl {

    /*local repo functions*/
    suspend fun hInsertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun hDeleteShoppingItem(shoppingItem: ShoppingItem)

    fun hGetAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun hGetTotalPriceItems(): LiveData<Float>


    /*Remote repo functions*/
    suspend fun hSearchImages(
        searchQuery: String
    ): Resource<ImageResponse>

}