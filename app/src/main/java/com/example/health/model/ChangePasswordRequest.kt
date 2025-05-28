package com.example.health.model

data class ChangePasswordRequest(
    val userId: Int,
    val oldPassword: String,
    val newPassword: String
)