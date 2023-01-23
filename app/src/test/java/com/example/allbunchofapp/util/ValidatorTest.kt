package com.example.allbunchofapp.util

import org.junit.Assert.*
import org.junit.Test

class ValidatorTest{
    //Arrange
    //Act
    //Assert
    @Test
    fun isValidName(){
        val result = Validator.isValidName("")
        assertEquals(false,result)
    }

    @Test
    fun isValidName_inputStringLevel_expectedTrue(){
        val result = Validator.isValidName("hello")
        assertEquals(true,result)
    }

    @Test
    fun isValidEmail(){

    }
    @Test
    fun isValidPassword(){

    }

}