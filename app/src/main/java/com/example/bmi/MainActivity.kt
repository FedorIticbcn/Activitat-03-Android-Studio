package com.example.bmi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import com.google.android.material.slider.Slider

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)
        setContentView(R.layout.activity_main)

        val mainAct = findViewById<ConstraintLayout>(R.id.mainAct)
        val maleCard = findViewById<LinearLayout>(R.id.maleCard)
        val femaleCard = findViewById<LinearLayout>(R.id.femaleCard)

        maleCard.setOnClickListener {
            maleCard.isSelected = true
            femaleCard.isSelected = false
        }

        femaleCard.setOnClickListener {
            femaleCard.isSelected = true
            maleCard.isSelected = false
        }

        val weightValue = findViewById<TextView>(R.id.weightValue)
        val ageValue = findViewById<TextView>(R.id.ageValue)
        val heightSlider = findViewById<Slider>(R.id.heightSlider)
        val heightValue = findViewById<TextView>(R.id.heightValue)

        val btnWeightMinus = findViewById<TextView>(R.id.btnWeightMinus)
        val btnWeightPlus = findViewById<TextView>(R.id.btnWeightPlus)
        val btnAgeMinus = findViewById<TextView>(R.id.btnAgeMinus)
        val btnAgePlus = findViewById<TextView>(R.id.btnAgePlus)

        heightSlider.addOnChangeListener { _, value, _ ->
            heightValue.text = value.toInt().toString()
        }

        btnWeightMinus.setOnClickListener {
            var weight = weightValue.text.toString().toIntOrNull() ?: 170
            if (weight > 1) {
                weight--
                weightValue.text = weight.toString()
            }
        }

        btnWeightPlus.setOnClickListener {
            var weight = weightValue.text.toString().toIntOrNull() ?: 170
            if (weight < 500) {
                weight++
                weightValue.text = weight.toString()
            }
        }

        btnAgeMinus.setOnClickListener {
            var age = ageValue.text.toString().toIntOrNull() ?: 18
            if (age > 1) {
                age--
                ageValue.text = age.toString()
            }
        }

        btnAgePlus.setOnClickListener {
            var age = ageValue.text.toString().toIntOrNull() ?: 18
            if (age < 120) {
                age++
                ageValue.text = age.toString()
            }
        }

        val calculateButton = findViewById<Button>(R.id.btnReCalculate)

        calculateButton.setOnClickListener {
            val height = heightSlider.value.toDouble()
            val weight = weightValue.text.toString().toDouble()

            val heightMeters = height * 0.0254
            val weightKg = weight * 0.453592

            val bmi = weightKg / (heightMeters * heightMeters)

            val intent = Intent(this, ActivityResultat::class.java)
            intent.putExtra("BMI", bmi)

            startActivity(intent)
        }
    }
}
