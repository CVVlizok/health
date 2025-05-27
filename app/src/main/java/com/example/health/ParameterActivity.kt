package com.example.health

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.health.model.EntryAdapter
import com.example.health.model.HealthParameterEntry
import com.example.health.server.HealthApiService
import com.example.health.server.KtorApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant

class ParameterActivity : AppCompatActivity() {

    private lateinit var parameterTitle: TextView
    private lateinit var userId: String
    private var paramId: Int = 0
    private lateinit var parameterName: String
    private lateinit var adapter: EntryAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parameter)

        val backButton = findViewById<ImageView>(R.id.backButton)
        parameterTitle = findViewById(R.id.parameterTitle)
        val parameterInfo = findViewById<TextView>(R.id.parameterInfo)
        val addButton = findViewById<Button>(R.id.addButton)

        parameterName = intent.getStringExtra("PARAM_NAME") ?: "Параметр"
        val paramInfo = intent.getStringExtra("PARAM_INFO") ?: "Описание отсутствует"
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val userId = prefs.getInt("user_id", -1).toString()
        paramId = getParameterId(parameterName)

        parameterTitle.text = parameterName
        parameterInfo.text = paramInfo

        val recyclerView = findViewById<RecyclerView>(R.id.dataList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EntryAdapter()
        recyclerView.adapter = adapter

        backButton.setOnClickListener { finish() }

        addButton.setOnClickListener {
            showInputDialog()
        }

        loadEntries()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showInputDialog() {
        val input = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("Введите значение")
            .setView(input)
            .setPositiveButton("Сохранить") { _, _ ->
                val value = input.text.toString().trim()
                if (value.isNotEmpty()) {
                    saveEntry(value)
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveEntry(value: String) {
        val entry = mapOf("value" to value)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val api = KtorApiClient.getClient(this@ParameterActivity)
                    .create(HealthApiService::class.java)
                api.addParameterEntry(userId, paramId, entry)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ParameterActivity, "Данные добавлены", Toast.LENGTH_SHORT).show()
                    loadEntries()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ParameterActivity, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun loadEntries() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val api = KtorApiClient.getClient(this@ParameterActivity)
                    .create(HealthApiService::class.java)
                val entries = api.getParameterEntries(userId, paramId)
                withContext(Dispatchers.Main) {
                    adapter.submitList(entries)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ParameterActivity, "Ошибка загрузки истории", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getParameterId(name: String): Int {
        return when (name) {
            "Пульс" -> 1
            "Частота дыхания" -> 2
            "Давление" -> 3
            "Глюкоза в крови" -> 4
            "Кислород в крови" -> 5
            "Температура тела" -> 6
            else -> 0
        }
    }
}
