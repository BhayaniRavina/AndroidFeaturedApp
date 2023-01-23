package com.example.allbunchofapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class LoginActivityTest{
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginFieldValidations_expectedCorrect(){
        onView(withId(R.id.etEmail)).apply {
            perform(typeText("Abcd123@email.com")).check(matches(withText("Abcd123@email.com")))
                .check(matches(hasFocus()))
                .check(matches(hasContentDescription()))
                .check(matches(withContentDescription(R.string.email)))
        }

        onView(withId(R.id.etPassword)).apply {
            perform(typeText("Xyz1234#"), closeSoftKeyboard()).check(matches(withText("Xyz1234#")))
                .check(matches(hasFocus()))
                .check(matches(hasContentDescription()))
                .check(matches(withContentDescription(R.string.password)))
                .perform(replaceText("Abcd1234#"))
        }

        onView(withId(R.id.btnLogin)).apply {
            perform(click())
        }
    }
}