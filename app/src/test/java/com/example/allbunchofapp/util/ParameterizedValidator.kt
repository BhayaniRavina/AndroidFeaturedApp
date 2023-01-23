package com.example.allbunchofapp.util

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

enum class ValidationFieldType{
    USERNAME,
    EMAIL,
    PASSWORD
}

@RunWith(value = Parameterized::class)
class ParameterizedValidator(val input : String, val expectedValue : Boolean,val type : ValidationFieldType) {

    @Test
    fun test(){
        when (type) {
            ValidationFieldType.USERNAME -> {
                val result = Validator.isValidName(input)
                assertEquals(expectedValue,result)
            }
            ValidationFieldType.EMAIL -> {
                val result = Validator.isValidEmail(input)
                assertEquals(expectedValue,result)
            }
            ValidationFieldType.PASSWORD -> {
                val result = Validator.isValidPassword(input)
                assertEquals(expectedValue,result)
            }
        }
    }

    companion object{

        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} isPalindrome - {1}")
        fun data() : List<Array<Any>>{
            return listOf(
                arrayOf("",false, ValidationFieldType.USERNAME),
                arrayOf("a",false, ValidationFieldType.USERNAME),
                arrayOf("ab",false, ValidationFieldType.USERNAME),
                arrayOf("abc",true, ValidationFieldType.USERNAME),

                /*arrayOf("Abcd123",false, ValidationFieldType.EMAIL),
                arrayOf("Abcd123@",false, ValidationFieldType.EMAIL),
                arrayOf("Abcd123@email",false, ValidationFieldType.EMAIL),
                arrayOf("Abcd123@email.com",true, ValidationFieldType.EMAIL),*/

                arrayOf("",false, ValidationFieldType.PASSWORD),
                arrayOf("a",false, ValidationFieldType.PASSWORD),
                arrayOf("ab",false, ValidationFieldType.PASSWORD),
                arrayOf("abc",false, ValidationFieldType.PASSWORD),
                arrayOf("Abcd1234$",true, ValidationFieldType.PASSWORD),
            )
        }
    }
}