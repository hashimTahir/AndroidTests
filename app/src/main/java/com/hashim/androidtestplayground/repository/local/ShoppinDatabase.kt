/*
 * Copyright (c) 2020/  8/ 24.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppinDatabase : RoomDatabase() {
    abstract fun hGetShoppingDao(): ShoppingDao
}