/*
 * Copyright (c) 2020/  8/ 21.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.other

import android.content.Context

class ResourseComparer {
    fun hIsequal(context: Context, restId: Int, string: String): Boolean {
        return context.getString(restId).equals(string)
    }
}