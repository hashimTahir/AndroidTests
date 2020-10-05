/*
 * Copyright (c) 2020/  10/ 5.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashim.androidtestplayground.other.Event
import com.hashim.androidtestplayground.other.Resource
import com.hashim.androidtestplayground.repository.DefaultRepo
import com.hashim.androidtestplayground.repository.local.ShoppingItem
import com.hashim.androidtestplayground.repository.remote.models.ImageResponse
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val hDefaultRepo: DefaultRepo
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
    private fun hInsertShoppingItem(
        name: String, amount: String,
        price: String
    ) {

    }

    /*Search for images online*/
    private fun hSearchImage(imageQuery: String) {

    }
}