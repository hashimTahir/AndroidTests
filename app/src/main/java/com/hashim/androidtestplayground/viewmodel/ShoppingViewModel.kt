/*
 * Copyright (c) 2020/  10/ 5.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashim.androidtestplayground.other.Constants
import com.hashim.androidtestplayground.other.Event
import com.hashim.androidtestplayground.other.Resource
import com.hashim.androidtestplayground.repository.DefaultRepoImpl
import com.hashim.androidtestplayground.repository.local.ShoppingItem
import com.hashim.androidtestplayground.repository.remote.models.ImageResponse
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val hDefaultRepo: DefaultRepoImpl
) : ViewModel() {
    /*Live Data to hold the values from the db*/
    val hShoppingItemsListLD = hDefaultRepo.hGetAllShoppingItems()

    val hTotalPriceLD = hDefaultRepo.hGetTotalPriceItems()


    /*Holds thes image resourse from the remote repo*/
    private val hImagesMLD = MutableLiveData<Event<Resource<ImageResponse>>>()
    val hImagesLD: LiveData<Event<Resource<ImageResponse>>> = hImagesMLD


    /*Holds current image Url to be displayed in the fragment*/
    private val hImageUrlMLD = MutableLiveData<String>()
    val hImageUrlLD: LiveData<String> = hImageUrlMLD


    /*Holds the current shopping item for validation*/
    private val hInsertShoppingItemStatusMLD = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val hInsertShoppingItemStatusLD: LiveData<Event<Resource<ShoppingItem>>> =
        hInsertShoppingItemStatusMLD

//////////////////////////////////////////////////////////////////////////////////
    /*************************Functions*****************************************/
//////////////////////////////////////////////////////////////////////////////////

    private fun hSetImageUrl(url: String) {
        hImageUrlMLD.value = url
    }

    private fun hDeleteShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            hDefaultRepo.hDeleteShoppingItem(shoppingItem)
        }
    }


    private fun hInsertShoppingItemInDb(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            hDefaultRepo.hInsertShoppingItem(shoppingItem)
        }
    }

    /*Will do the validation*/
    public fun hInsertShoppingItem(
        name: String,
        amountString: String,
        price: String
    ) {
        if (name.isEmpty() || amountString.isEmpty() || price.isEmpty()) {
            hInsertShoppingItemStatusMLD.value =
                Event(Resource.error("The fields must not be empty", null))
            return
        }
        if (name.length > Constants.MAX_NAME_LENGTH) {
            hInsertShoppingItemStatusMLD.value =
                Event(
                    Resource.error(
                        "The name of the item must not exceed ${Constants.MAX_NAME_LENGTH}",
                        null
                    )
                )
            return
        }
        if (price.length > Constants.MAX_PRICE_LENGTH) {
            hInsertShoppingItemStatusMLD.value =
                Event(
                    Resource.error(
                        "The name of the item must not exceed ${Constants.MAX_PRICE_LENGTH} characters",
                        null
                    )
                )
            return
        }
        val amount = try {
            amountString.toInt()
        } catch (e: Exception) {
            hInsertShoppingItemStatusMLD.value =
                Event(
                    Resource.error(
                        "Please enter valid amount",
                        null
                    )
                )
            return
        }
        val shoppingItem = ShoppingItem(name, amount, price.toFloat(), hImageUrlLD.value ?: "")
        hInsertShoppingItemInDb(shoppingItem)
        hSetImageUrl("")
        hInsertShoppingItemStatusMLD.value = Event(Resource.success(shoppingItem))

    }

    /*Search for images online*/
    private fun hSearchImage(imageQuery: String) {
        if (imageQuery.isEmpty()) {
            return
        }
        hImagesMLD.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val hResponse = hDefaultRepo.hSearchImages(imageQuery)
            hImagesMLD.value = Event(hResponse)
        }
    }
}