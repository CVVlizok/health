package com.example.health.api.ai

data class GeneralRequest(
    val model: String,             // Модель для работы с MistralAI
    val messages: List<Message>,   // Сообщения, отправляемые на сервер
)