package com.example.health.model

data class UserRegisterRequest(
    val login: String,
    val email: String,
    val password: String
)
