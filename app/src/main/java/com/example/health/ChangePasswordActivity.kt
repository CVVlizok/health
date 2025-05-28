package com.example.health

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health.model.ChangePasswordRequest
import com.example.health.server.HealthApiService
import com.example.health.server.KtorApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            val userId = getSharedPreferences("app_prefs", MODE_PRIVATE).getInt("user_id", -1)

            if (newPassword != confirmPassword) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                val api = KtorApiClient.getClient(this@ChangePasswordActivity).create(
                    HealthApiService::class.java)
                val response = api.changePassword(ChangePasswordRequest(userId, oldPassword, newPassword))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ChangePasswordActivity, "Пароль успешно изменён", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@ChangePasswordActivity, "Ошибка: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}