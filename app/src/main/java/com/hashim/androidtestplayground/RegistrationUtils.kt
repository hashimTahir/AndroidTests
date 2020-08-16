/*
 * Copyright (c) 2020/  8/ 16.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground

object RegistrationUtils {
val  hExistingUser = listOf("Hashim","Carl")
    /*
    * ---- input is not valid if---
    *  if user/password is empty or null
    * user name is taken
    * if confirm != password
    * if password is less than 8
    *
    * */
    fun hValidateRegistrationInput(
        userName: String,
        password: String,
        confrimPassword: String
    ): Boolean {

        return true
    }

}