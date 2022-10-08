package com.example.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val input = binding.editTextInput.text

        //if input not null get
    }
}






