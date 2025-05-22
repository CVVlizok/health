package com.example.health

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<Button>(R.id.buttonPulse).setOnClickListener {
            openParameterScreen("Пульс", "Частота сердечных сокращений в минуту.")
        }

        view.findViewById<Button>(R.id.buttonBreath).setOnClickListener {
            openParameterScreen("Частота дыхания", "Количество дыхательных движений в минуту.")
        }

        view.findViewById<Button>(R.id.buttonPressure).setOnClickListener {
            openParameterScreen("Давление", "Систолическое и диастолическое давление (мм рт. ст.).")
        }

        view.findViewById<Button>(R.id.buttonGlucose).setOnClickListener {
            openParameterScreen("Глюкоза в крови", "Уровень сахара в крови (ммоль/л).")
        }

        view.findViewById<Button>(R.id.buttonOxygen).setOnClickListener {
            openParameterScreen("Кислород в крови", "Содержание кислорода в крови (SpO2).")
        }

        view.findViewById<Button>(R.id.buttonTemperature).setOnClickListener {
            openParameterScreen("Температура тела", "Измерение температуры тела (°C).")
        }

        return view
    }

    private fun openParameterScreen(name: String, info: String) {
        val intent = Intent(activity, ParameterActivity::class.java)
        intent.putExtra("PARAM_NAME", name)
        intent.putExtra("PARAM_INFO", info)
        startActivity(intent)
    }
}
