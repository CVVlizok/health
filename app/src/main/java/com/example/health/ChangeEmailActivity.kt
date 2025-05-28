package com.example.health

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health.model.ChangeEmailRequest
import com.example.health.server.HealthApiService
import com.example.health.server.KtorApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            val userId = getSharedPreferences("app_prefs", MODE_PRIVATE).getInt("user_id", -1)

            if (newEmail != confirmEmail) {
                Toast.makeText(this, "Новая почта не совпадает", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                val api = KtorApiClient.getClient(this@ChangeEmailActivity).create(HealthApiService::class.java)
                val response = api.changeEmail(ChangeEmailRequest(userId, oldEmail, newEmail))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ChangeEmailActivity, "Почта успешно изменена", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@ChangeEmailActivity, "Ошибка: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
