/*
 * Copyright (c) 2020/  10/ 5.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground.other

object RegistrationUtils {
   private val hExistingUser = listOf("Hashim", "Carl")

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

        if (userName.isEmpty() || password.isEmpty()) {
            return false
        }
        if (userName in hExistingUser) {
            return false
        }
        if (!password.equals(confrimPassword)) {
            return false
        }
        if (password.length < 2) {
            return false
        }
        return true
    }

}