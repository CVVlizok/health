package com.example.health.api.ai

data class Choice(
    val index: Int,
    val message: Message,
    val finish_reason: String,
    val logprobs: Any?
)