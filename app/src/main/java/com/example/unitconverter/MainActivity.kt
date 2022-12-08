package com.example.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.unitconverter.databinding.ActivityMainBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class MainActivity : AppCompatActivity() {

    //initializes the binding variable when needed
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set click listeners on the radio buttons for metric and US

        binding.radioButtonMetric.setOnClickListener{ changeToMetric()

        }

        binding.radioButtonUS.setOnClickListener{changeToUS()

        }

        //set an on click listener for the calculate button
        binding.calculateButton.setOnClickListener{
            getMeasurement()
        }




    }
    fun changeToMetric (){
        (binding.menuParent.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(R.array.metric_units)
    }

    fun changeToUS () {
        (binding.menuParent.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(R.array.US_units)
    }

    fun getMeasurement() {
        //get input
        val input = binding.editTextInput.text.toString()
        val conversionTo = binding.menu.text.toString()
        var unitChoice = ""
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_US) {
            unitChoice = "US"
        } else if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_metric) {
            unitChoice = "metric"
        }


        //if input not null get calculation
        if(!input.isEmpty() && !conversionTo.isEmpty()) {

            //binding.conversionResult.setText(input)
            //get name of selection
            when (conversionTo) {
                "fluid ounces to milliliters" -> calculate(input.toDouble(), unitChoice, conversionTo, 29.5735296, "metric")
                "quarts to liters" -> calculate(input.toDouble(), unitChoice, conversionTo, 0.94635295,"metric")
                "ounces to grams" -> calculate(input.toDouble(), unitChoice, conversionTo, 28.3495231, "metric")
                "pounds to kilograms" -> calculate(input.toDouble(), unitChoice, conversionTo, 0.45359237, "metric")
                "Fahrenheit to Celsius" ->calculate(input.toDouble(), unitChoice, conversionTo, 0.0, "metric")

                //to US system
                "milliliters to fluid ounces" -> calculate(input.toDouble(), unitChoice, conversionTo, 0.03381402, "US")
                "liters to quarts" -> calculate(input.toDouble(), unitChoice, conversionTo, 1.05668821, "US")
                "grams to ounces" -> calculate(input.toDouble(), unitChoice, conversionTo, 0.03527396, "US")
                "kilograms to pounds" ->calculate(input.toDouble(), unitChoice, conversionTo, 2.20462262, "US")
                "Celsius to Fahrenheit" -> calculate(input.toDouble(), unitChoice, conversionTo, 0.0, "US")


            }
        }
        val stringToastBuilder = StringBuilder()
        stringToastBuilder.append("Please enter:")

        if(input.isEmpty()) {
            stringToastBuilder.append(" [amount]")
        }

        if (unitChoice ==""){
            stringToastBuilder.append(" [unit]")
        }

        if (conversionTo.isEmpty()){
            stringToastBuilder.append(" [measurement]")
        }
        if (input.isEmpty() || unitChoice == "" || conversionTo.isEmpty()) {
            Toast.makeText(this, stringToastBuilder.toString(), Toast.LENGTH_SHORT).show()
        }



    }




    fun calculate (input : Double, unitChoice : String, measurementTo : String, factor:Double, expectedUnit : String){
        var result = 0.0;
        if (unitChoice != expectedUnit){
                val unitsMessage = Toast.makeText(this, R.string.toast_mismatch, Toast.LENGTH_LONG)
                unitsMessage.show()


        }

        if( measurementTo == "Fahrenheit to Celsius" || measurementTo == "Celsius to Fahrenheit") {
            when (measurementTo) {
                "Fahrenheit to Celsius" -> result = (input - 32) * 5.0 / 9.0
                "Celsius to Fahrenheit" -> result = input * (9.0 / 5.0) + 32
            }
        }
        else if (input >= 0){
            result = input *  factor
        }
        else{
            var toast = Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_LONG)
            toast.show()
        }




        val formattedResult = String.format("%.2f", result)
        binding.conversionResult.setText(formattedResult)
    }
}






