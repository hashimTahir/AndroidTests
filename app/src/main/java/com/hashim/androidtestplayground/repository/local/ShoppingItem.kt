/*
 * Copyright (c) 2020/  8/ 24.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingItems")
data class ShoppingItem(
    var hName: String,
    var hAmount: Int,
    var hPrice: Float,
    var hImageUrl: String,
    @PrimaryKey(autoGenerate = true)
    val hId: Int? = null
)