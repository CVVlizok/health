package com.example.health

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ParameterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parameter)

        val backButton = findViewById<ImageView>(R.id.backButton)
        val parameterTitle = findViewById<TextView>(R.id.parameterTitle)
        val parameterInfo = findViewById<TextView>(R.id.parameterInfo)
        val addButton = findViewById<Button>(R.id.addButton)

        val paramName = intent.getStringExtra("PARAM_NAME") ?: "Параметр"
        val paramInfo = intent.getStringExtra("PARAM_INFO") ?: "Описание отсутствует"

        parameterTitle.text = paramName
        parameterInfo.text = paramInfo

        backButton.setOnClickListener {
            finish()
        }

        addButton.setOnClickListener {
            // В будущем здесь будет окно добавления данных
        }
    }
}
