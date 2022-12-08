package com.example.unitconverter

import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    //launch activity before every test (a rule)
    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    private var decorView: View? = null

    @Before
    fun setup() {
        activity.getScenario().onActivity { activity ->
            decorView = activity.getWindow().getDecorView()

        }

    }

    @After
    fun teardown() {
        Thread.sleep(5000)
    }







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
        else if (unit == "metric"){
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


        onView(withId(R.id.editTextInput)).perform(clearText())
            .perform(ViewActions.closeSoftKeyboard())
    }

    @Test
    fun validateToastMessageEmpty(){
        onView(withId(R.id.calculate_button))
            .perform(click())


        val toastString ="Please enter: [amount] [unit] [measurement]"

        onView(withText(toastString))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))

    }


    fun validateToastMessageAmount(buttonid :Int){
        onView(withId(buttonid))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        val toastString ="Please enter: [amount] [measurement]"
        // match toast with string toast_message_empty
        onView(withText(toastString))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))

    }

    @Test
    fun validateToastMessageUnit(){
        onView(withId(R.id.editTextInput))
            .perform(typeText("500"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.calculate_button))
            .perform(click())

        val toastString ="Please enter: [unit] [measurement]"
        // match toast with string toast_unit
        onView(withText(toastString))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))

    }

    @Test
    fun validateToastMessageConvert(){
        onView(withId(R.id.editTextInput))
            .perform(typeText("500"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.radio_button_US))
            .perform(click());

        onView(withId(R.id.calculate_button))
            .perform(click())

        // match toast with string

        val toastString ="Please enter: [measurement]"
        onView(withText(toastString))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))

    }

    @Test
    fun validateToastMessageMismatch(){
        onView(withId(R.id.editTextInput))
            .perform(typeText("500"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.radio_button_US))
            .perform(click());

        onView(withHint("Select measurement"))
            .perform(click())

        onData(Matchers.anything())
            .inRoot(RootMatchers.isPlatformPopup())
            .atPosition(0)
            .perform(click())

        onView(withId(R.id.radio_button_metric))
            .perform(click());

        onView(withId(R.id.calculate_button))
            .perform(click())

        // match toast with string toast_mismatch
        onView(withText(R.string.toast_mismatch))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))

    }

    @Test
    fun validateWithUS(){
        validateToastMessageAmount(R.id.radio_button_US)
    }


    @Test
    fun validateWithMetric(){
        validateToastMessageAmount(R.id.radio_button_metric)
    }






    @Test
    fun regularCalculations(){
        //US
        //milliliters to fluid ounces
        calculate("500", "US", "16.91", 0)

        //liters to quarts (position 1)
        calculate("500", "US", "528.34", 1)

        //grams to ounces (position 2)
        calculate("500", "US", "17.64", 2)

        // kg to lbs (position 3)
        calculate("500", "US", "1102.31", 3)

        //Celsius to Fahrenheit (position 4)
        calculate("5", "US", "41.00", 4)


        //metric
        //fluid ounces to milliliters
        calculate("500", "metric", "14786.76", 0)

        //quarts to liters(position 1)
        calculate("500", "metric", "473.18", 1)

        //ounces to grams (position 2)
        calculate("500", "metric", "14174.76", 2)

        //lbs to kgs (position 3)
        calculate("500", "metric", "226.80", 3)

        //Fahrenheit to Celsius (position 4)
        calculate("5", "metric", "-15.00", 4)


    }



//    @Test
//    fun negativeCalculations(){
//        calculate("-500", "US", "16.91", 0)
//    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.unitconverter", appContext.packageName)
    }




}