/*
 * Copyright (c) 2020/  8/ 21.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth;
import org.junit.Before
import org.junit.Test

class ResourseComparerTest {
    private lateinit var hResourseComparer: ResourseComparer

    /*Setup on anything added in before is executed before every test*/
    @Before
    fun hSetup() {
        hResourseComparer = ResourseComparer()
///*false commi*/

    }

    /*Get context to get the resourses*/
    @Test
    fun hStringResourseSameAsGivenString_retrunsTrue() {
        var applicationContext = ApplicationProvider.getApplicationContext<Context>()

        val hResult = hResourseComparer.hIsequal(
            applicationContext,
            R.string.app_name,
            "Android Test Playground"
        )
        Truth.assertThat(hResult).isTrue()
    }

    @Test
    fun hStringResourseNotSameAsGivenString_retrunsFalse() {
        var applicationContext = ApplicationProvider.getApplicationContext<Context>()
        val hResult = hResourseComparer.hIsequal(
            applicationContext,
            R.string.app_name,
            "Hashim"
        )
        Truth.assertThat(hResult).isFalse()
    }
}