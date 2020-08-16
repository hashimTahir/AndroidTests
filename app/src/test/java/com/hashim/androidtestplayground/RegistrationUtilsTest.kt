/*
 * Copyright (c) 2020/  8/ 16.  Created by Hashim Tahir
 */

package com.hashim.androidtestplayground

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class RegistrationUtilsTest {

    @Test
    fun `empty user name return false`() {

        val hResult = RegistrationUtils.hValidateRegistrationInput(
            "",
            "123",
            "123"
        )

        assertThat(hResult).isFalse()

    }

    @Test
    fun `valid user name correctly repeated passwords return true`() {

        val hResult = RegistrationUtils.hValidateRegistrationInput(
            "hashim",
            "123",
            "123"
        )

        assertThat(hResult).isTrue()
    }


    @Test
    fun `user name exists return false`() {

        val hResult = RegistrationUtils.hValidateRegistrationInput(
            "hashim",
            "123",
            "123"
        )

        assertThat(hResult).isFalse()
    }

    @Test
    fun `password empty retun false`() {

        val hResult = RegistrationUtils.hValidateRegistrationInput(
            "hashim",
            "",
            "123"
        )

        assertThat(hResult).isFalse()
    }

    @Test
    fun `password and non matching repeating passowrd retrun false`() {

        val hResult = RegistrationUtils.hValidateRegistrationInput(
            "hashim",
            "123",
            "1234"
        )

        assertThat(hResult).isFalse()
    }
    @Test
    fun `password less than 3 returns false`() {

        val hResult = RegistrationUtils.hValidateRegistrationInput(
            "hashim",
            "12",
            "123"
        )

        assertThat(hResult).isFalse()
    }
}