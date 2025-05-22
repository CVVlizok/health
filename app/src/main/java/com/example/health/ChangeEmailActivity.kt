package com.example.health

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChangeEmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)

        val oldEmailField = findViewById<EditText>(R.id.oldEmailField)
        val newEmailField = findViewById<EditText>(R.id.newEmailField)
        val confirmEmailField = findViewById<EditText>(R.id.confirmEmailField)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            val oldEmail = oldEmailField.text.toString().trim()
            val newEmail = newEmailField.text.toString().trim()
            val confirmEmail = confirmEmailField.text.toString().trim()

            if (oldEmail.isEmpty() || newEmail.isEmpty() || confirmEmail.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newEmail != confirmEmail) {
                Toast.makeText(this, "Новая почта не совпадает", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Почта успешно изменена", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
