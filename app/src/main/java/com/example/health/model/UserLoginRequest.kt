package com.example.health.model

data class UserLoginRequest(
    val login: String,
    val password: String
)

data class UserLoginResponse(
    val token: String
)
