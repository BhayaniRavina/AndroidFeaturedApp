package com.example.allbunchofapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test

class RegistrationActivityTest{

    //First start this activity and then execute below test cases
    @get:Rule
    val activityScenarioRule = activityScenarioRule<RegistrationActivity>()
    @Test
    fun testUsername_expectedCorrect(){
        onView(withId(R.id.etUserName)).apply {
            check(matches(withText("")))
            check(matches(hasContentDescription()))
            check(matches(withContentDescription(R.string.username)))
        }

        onView(withId(R.id.etEmail)).apply {
            check(matches(withText("Abcd1234@email.com")))
            check(matches(withText("Abcd1234@email.com")))
        }

        onView((withId(R.id.etPassword))).apply {
            perform(typeText("Abxy123#"), closeSoftKeyboard())
            perform(clearText())
            perform(typeTextIntoFocusedView("Abxy123#"), closeSoftKeyboard())
            perform(replaceText("Abcd1234#"), closeSoftKeyboard())
        }

        onView(withId(R.id.btnSignUp)).perform(click())
    }

    @Test
    fun testRedirectToLoginScreen_expectedCorrect(){

    }
}