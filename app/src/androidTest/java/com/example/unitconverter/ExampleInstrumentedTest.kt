package com.example.unitconverter

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.hamcrest.Matchers.containsString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    //launch activity before every test (a rule)
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    //calculate 500 milliliters to fluid ounces
    @Test

    fun calculate500MillilitersToFlOz(){
        //use Espresso to type an amount in editText field
        onView(withId(R.id.editTextInput))
            .perform(typeText("500"))
            .perform(ViewActions.closeSoftKeyboard())

        //choose the unit system
        onView(withId(R.id.radio_button_US))
            .perform(click());

        //make the view pop up for next Espresso function
        onView(withHint("Select measurement"))
            .perform(click())

        //on Popup click the first option

        onData(Matchers.anything())
            .inRoot(RootMatchers.isPlatformPopup())
            .atPosition(0)
            .perform(click())

        //click the calculate button
        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.conversionResult))
            .check(matches(withText(containsString("16.91"))))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.unitconverter", appContext.packageName)
    }




}