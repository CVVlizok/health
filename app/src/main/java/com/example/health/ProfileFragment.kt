package com.example.health

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val emailLayout = view.findViewById<LinearLayout>(R.id.emailLayout)
        val passwordLayout = view.findViewById<LinearLayout>(R.id.passwordLayout)

        // Открываем активность смены почты
        emailLayout.setOnClickListener {
            val intent = Intent(activity, ChangeEmailActivity::class.java)
            startActivity(intent)
        }

        // Открываем активность смены пароля
        passwordLayout.setOnClickListener {
            val intent = Intent(activity, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
