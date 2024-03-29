/*
 * Copyright (c) 2020/  8/ 27.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.di

import android.content.Context
import androidx.room.Room
import com.hashim.androidtestplayground.other.Constants
import com.hashim.androidtestplayground.repository.DefaultRepoImpl
import com.hashim.androidtestplayground.repository.DefaultRepository
import com.hashim.androidtestplayground.repository.local.ShoppinDatabase
import com.hashim.androidtestplayground.repository.local.ShoppingDao
import com.hashim.androidtestplayground.repository.remote.PixarbayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun hProvidesShoppingDataBase(
        @ApplicationContext context: Context
    ): ShoppinDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            ShoppinDatabase::class.java,
        )
            .allowMainThreadQueries()
            .build()
    }

//    @Singleton
//    @Provides
//    fun hProvidesShoppingDao(shoppinDatabase: ShoppinDatabase): ShoppingDao {
//        return shoppinDatabase.hGetShoppingDao()
//    }
//
//    @Singleton
//    @Provides
//    fun hProvidesPixarBayApi(): PixarbayApi {
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(Constants.H_BASE_URL)
//            .build()
//            .create(PixarbayApi::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun hProvidesDefaultRepo(
//        hShoppingDao: ShoppingDao,
//        hPixarbayApi: PixarbayApi
//    ): DefaultRepository {
//        return DefaultRepoImpl(hShoppingDao, hPixarbayApi)
//    }


}