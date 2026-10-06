package com.example.bmi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

class ActivityResultat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)
        setContentView(R.layout.activity_resultat)

        val rootAct = findViewById<ConstraintLayout>(R.id.resultAct)
        ViewCompat.setOnApplyWindowInsetsListener(rootAct) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                top = insets.top,
                bottom = insets.bottom,
                left = insets.left,
                right = insets.right
            )
            WindowInsetsCompat.CONSUMED
        }

        val bmi = intent.getDoubleExtra("BMI", 0.0)

        val btnBack = findViewById<TextView>(R.id.btnBack)
        val resultText = findViewById<TextView>(R.id.resultText)
        val bmiValue = findViewById<TextView>(R.id.bmiValue)
        val bmiDescription = findViewById<TextView>(R.id.bmiDescription)
        val btnCalculate = findViewById<Button>(R.id.btnReCalculate)

        btnBack.setOnClickListener {
            finish()
        }

        bmiValue.text = String.format("%.2f", bmi)

        val (category, description, colorRes) = when {
            bmi < 18.5 -> Triple("UNDERWEIGHT", "Underweight", ContextCompat.getColor(this, R.color.orange))
            bmi < 25 -> Triple("NORMAL", "Normal", ContextCompat.getColor(this, R.color.green))
            else -> Triple("OVERWEIGHT", "Overweight", ContextCompat.getColor(this, R.color.orange))
        }

        resultText.text = category
        resultText.setTextColor(colorRes)
        bmiDescription.text = description

        btnCalculate.setOnClickListener {
            finish()
        }
    }
}
