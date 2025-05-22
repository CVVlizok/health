package com.example.health

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChangePasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val oldPasswordField = findViewById<EditText>(R.id.oldPasswordField)
        val newPasswordField = findViewById<EditText>(R.id.newPasswordField)
        val confirmPasswordField = findViewById<EditText>(R.id.confirmPasswordField)
        val saveButton = findViewById<Button>(R.id.saveButton)

        // Кнопка "Сохранить"
        saveButton.setOnClickListener {
            val oldPassword = oldPasswordField.text.toString().trim()
            val newPassword = newPasswordField.text.toString().trim()
            val confirmPassword = confirmPasswordField.text.toString().trim()

            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                Toast.makeText(this, "Новый пароль не совпадает", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Пока просто показываем сообщение и закрываем активность
            Toast.makeText(this, "Пароль успешно изменён", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
