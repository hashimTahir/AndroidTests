/*
 * Copyright (c) 2020/  8/ 28.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repository

import androidx.lifecycle.LiveData
import com.hashim.androidtestplayground.other.Constants
import com.hashim.androidtestplayground.other.Resource
import com.hashim.androidtestplayground.repository.local.ShoppingDao
import com.hashim.androidtestplayground.repository.local.ShoppingItem
import com.hashim.androidtestplayground.repository.remote.PixarbayApi
import com.hashim.androidtestplayground.repository.remote.models.ImageResponse
import javax.inject.Inject

class DefaultRepo @Inject constructor(
    private val hShoppingDao: ShoppingDao,
    private val hPixarbayApi: PixarbayApi
) : DefaultRepoImpl {
    override suspend fun hInsertShoppingItem(shoppingItem: ShoppingItem) {
        hShoppingDao.hInsertShoppingItem(shoppingItem)
    }

    override suspend fun hDeleteShoppingItem(shoppingItem: ShoppingItem) {
        hShoppingDao.hDeleteShoppingItem(shoppingItem)
    }

    override fun hGetAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return hShoppingDao.hGetAllShoppingItems()
    }

    override fun hGetTotalPriceItems(): LiveData<Float> {
        return hShoppingDao.hGetTotalPriceItems()
    }

    override suspend fun hSearchImages(searchQuery: String): Resource<ImageResponse> {
        return try {
            val hResponse =
                hPixarbayApi.hSearchImages(
                    searchQuery,
                    Constants.H_PIXARBAY_API_KEY
                )
            if (hResponse.isSuccessful) {
                hResponse.body()?.let {
                    Resource.success(it)
                } ?: Resource.error("An unknown error occurerd", null)

            } else {
                Resource.error("An unknown error occurerd", null)
            }

        } catch (e: Exception) {
            Resource.error("Could'nt reach server, check your internet connection", null)
        }
    }
}