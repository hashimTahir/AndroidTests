/*
 * Copyright (c) 2020/  10/ 5.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hashim.androidtestplayground.other.Resource
import com.hashim.androidtestplayground.repository.local.ShoppingItem
import com.hashim.androidtestplayground.repository.remote.models.ImageResponse

/*Simulates the behaviour of Real repo in tests for view models*/
class FakeRepoTest : DefaultRepository {

    private val hShoppinItemList = mutableListOf<ShoppingItem>()

    private val hShoppingItemListMLD = MutableLiveData<List<ShoppingItem>>(hShoppinItemList)

    private val hShoppingItemPriceMLD = MutableLiveData<Float>()

    private var hShouldReturnNetworkError = false


    fun hSetShouldReturnNetworkError(hIsError: Boolean) {
        hShouldReturnNetworkError = hIsError
    }

    private fun hPostNewValues() {
        hShoppingItemListMLD.postValue(hShoppinItemList)
        hShoppingItemPriceMLD.postValue(hGetTotalPrice())
    }

    private fun hGetTotalPrice(): Float {
        return hShoppinItemList.sumByDouble {
            it.hPrice.toDouble()
        }.toFloat()
    }

    override suspend fun hInsertShoppingItem(shoppingItem: ShoppingItem) {
        hShoppinItemList.add(shoppingItem)
        hPostNewValues()
    }

    override suspend fun hDeleteShoppingItem(shoppingItem: ShoppingItem) {
        hShoppinItemList.remove(shoppingItem)
        hPostNewValues()
    }

    override fun hGetAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return hShoppingItemListMLD
    }

    override fun hGetTotalPriceItems(): LiveData<Float> {
        return hShoppingItemPriceMLD
    }

    override suspend fun hSearchImages(searchQuery: String): Resource<ImageResponse> {
        return if (hShouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }


}