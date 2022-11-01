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


    fun calculate( amount : String, unit : String, result: String, position : Int ){
        //use Espresso to type an amount in editText field
        onView(withId(R.id.editTextInput))
            .perform(typeText(amount))
            .perform(ViewActions.closeSoftKeyboard())

        //choose the unit system
        if(unit == "US") {
            onView(withId(R.id.radio_button_US))
                .perform(click());
        }
        else{
            onView(withId(R.id.radio_button_metric))
                .perform(click());
        }

        //make the view pop up for next Espresso function
        onView(withHint("Select measurement"))
            .perform(click())

        //on Popup click the first option

        onData(Matchers.anything())
            .inRoot(RootMatchers.isPlatformPopup())
            .atPosition(position)
            .perform(click())

        //click the calculate button
        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.conversionResult))
            .check(matches(withText(containsString(result))))
    }

    @Test
    fun regularCalculations(){
        //US
            //milliliters to fluid ounces
        calculate("500", "US", "16.91", 0)

        //liters to cups (position 1)
        calculate("500", "US", "16.91", 1)

        //grams to ounces (position 2)
        calculate("500", "US", "17.64", 2)

        // kg to lbs (position 3)

        //


    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.unitconverter", appContext.packageName)
    }




}