package com.example.health

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import android.widget.LinearLayout

fun showAddEntryDialog(context: Context, onSave: (String) -> Unit) {
    val input = EditText(context)
    input.hint = "Введите значение"
    input.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

    AlertDialog.Builder(context)
        .setTitle("Новое значение")
        .setView(input)
        .setPositiveButton("Сохранить") { _, _ ->
            val value = input.text.toString()
            if (value.isNotBlank()) onSave(value)
        }
        .setNegativeButton("Отмена", null)
        .show()
}
