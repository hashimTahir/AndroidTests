/*
 * Copyright (c) 2020/  8/ 28.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repository

import androidx.lifecycle.LiveData
import com.hashim.androidtestplayground.repository.local.ShoppingDao
import com.hashim.androidtestplayground.repository.local.ShoppingItem
import com.hashim.androidtestplayground.repository.remote.PixarbayApi
import com.hashim.androidtestplayground.repository.remote.models.ImageResponse
import retrofit2.Response
import javax.inject.Inject

class DefaultRepo @Inject constructor(
    private val hShoppingDao: ShoppingDao,
    private val hPixarbayApi: PixarbayApi
):DefaultRepoImpl {
    override suspend fun hInsertShoppingItem(shoppingItem: ShoppingItem) {
        TODO("Not yet implemented")
    }

    override suspend fun hDeleteShoppingItem(shoppingItem: ShoppingItem) {
        TODO("Not yet implemented")
    }

    override fun hGetAllShoppingItems(): LiveData<List<ShoppingItem>> {
        TODO("Not yet implemented")
    }

    override fun hGetTotalPriceItems(): LiveData<Float> {
        TODO("Not yet implemented")
    }

    override suspend fun hSearchImages(searchQuery: String): Response<ImageResponse> {
        TODO("Not yet implemented")
    }
}