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



        //if input not null get calculation
        if(!input.isEmpty() && !conversionTo.isEmpty()) {
            //binding.conversionResult.setText(input)
            //get name of selection
            when (conversionTo){
                "fluid ounces to milliliters" -> flOzToMilli(input.toDouble())
                "cups to liters" -> cupToLiter(input.toDouble())
                "ounces to grams"-> ozToGram(input.toDouble())
                "pounds to kilograms" -> lbToKg(input.toDouble())
                "Fahrenheit to Celsius" -> fToCDegree(input.toDouble())

                //to US system
                "milliliters to fluid ounces" -> milliToFlOz(input.toDouble())
                "liters to cups" -> literToCup(input.toDouble())
                "grams to ounces" -> gramToOz(input.toDouble())
                "kilograms to pounds" -> kgToLb(input.toDouble())
                "Celsius to Fahrenheit" -> cToFDegree(input.toDouble())

            }

        }
        else if(input.isEmpty() && conversionTo.isEmpty()){
            var toast = Toast.makeText(this, "Please enter the amount and select the conversion type.", Toast.LENGTH_LONG)
            toast.show()
        }
        else if (input.isEmpty()){
            var toast = Toast.makeText(this, "Please enter the amount.", Toast.LENGTH_LONG)
            toast.show()
        }

        else{
            var toast = Toast.makeText(this, "Please select the conversion type.", Toast.LENGTH_LONG)
            toast.show()
        }


    }

    //To Metric Units
    fun flOzToMilli(input : Double) {
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_US){
        val unitsMessage =
            Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }
        if (input >=0) {
            val result = input * 29.5735296
            val formattedResult = String.format("%.2f", result)
            binding.conversionResult.setText(formattedResult)
        }
        else{
            val signedMessage = Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_SHORT)
            signedMessage.show()
        }
    }

    fun cupToLiter(input: Double){
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_US){
            val unitsMessage =
                Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }
        if (input >=0) {
            val result = input * 0.23658824
            val formattedResult = String.format("%.2f", result)
            binding.conversionResult.setText(formattedResult)
        }
        else{
            var toast =  Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    fun ozToGram(input: Double){
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_US){
            val unitsMessage =
                Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }
        if (input >=0) {
            val result = input * 28.3495231
            val formattedResult = String.format("%.2f", result)
            binding.conversionResult.setText(formattedResult)
        }
        else{
            var toast = Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun lbToKg(input: Double){
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_US){
            val unitsMessage =
                Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }
        if (input >=0) {
            val result = input * 0.45359237
            val formattedResult = String.format("%.2f", result)
            binding.conversionResult.setText(formattedResult)
        }
        else{
            var toast = Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun fToCDegree(input: Double){
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_US){
            val unitsMessage =
                Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }
        if (input >=0) {
            val result = input * (5.0 / 9.0) - 32
            val formattedResult = String.format("%.2f", result)
            binding.conversionResult.setText(formattedResult)
        }
        var toast = Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_SHORT)
        toast.show()
    }

    //To US units

    fun milliToFlOz(input: Double){
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_metric){
            val unitsMessage =
                Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }
        if (input >=0) {
            val result = input * 0.03381402
            val formattedResult = String.format("%.2f", result)
            binding.conversionResult.setText(formattedResult)
        }
        var toast = Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_SHORT)
    }

    fun literToCup(input: Double){
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_metric){
            val unitsMessage =
                Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }
        if (input >=0) {
            val result = input * 4.22675284
            val formattedResult = String.format("%.2f", result)
            binding.conversionResult.setText(formattedResult)
        }
        var toast = Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_SHORT)
    }

    fun gramToOz(input: Double){
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_metric){
            val unitsMessage =
                Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }
        if (input >=0) {
            val result = input * 0.03527396
            val formattedResult = String.format("%.2f", result)
            binding.conversionResult.setText(formattedResult)
        }
        var toast = Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_SHORT)
    }

    fun kgToLb(input: Double){
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_metric){
            val unitsMessage =
                Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }
        if (input >=0) {
            val result = input * 2.20462262
            val formattedResult = String.format("%.2f", result)
            binding.conversionResult.setText(formattedResult)
        }
        var toast = Toast.makeText(this, "Please enter a positive value", Toast.LENGTH_SHORT)

    }

    fun cToFDegree(input: Double) {
        if (binding.radioGroup.checkedRadioButtonId == R.id.radio_button_metric){
            val unitsMessage =
                Toast.makeText(this, "Your measurement choice and unit system don't match", Toast.LENGTH_SHORT)
            unitsMessage.show()
            return
        }

        val result = input * (9.0 / 5.0) + 32

        val formattedResult = String.format("%.2f", result)
        binding.conversionResult.setText(formattedResult)
    }
}






