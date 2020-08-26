/*
 * Copyright (c) 2020/  8/ 27.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.repository.remote.models

data class ImageResponse(
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)