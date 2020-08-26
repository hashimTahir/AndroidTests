/*
 * Copyright (c) 2020/  8/ 27.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repository.remote

import com.hashim.androidtestplayground.repository.remote.models.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixarbayApi {

    @GET("/api/")
    suspend fun hSearchImages(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String
    ): Response<ImageResponse>

}