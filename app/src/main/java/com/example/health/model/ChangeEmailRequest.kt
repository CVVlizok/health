package com.example.health.model

data class ChangeEmailRequest(
    val userId: Int,
    val oldEmail: String,
    val newEmail: String
)