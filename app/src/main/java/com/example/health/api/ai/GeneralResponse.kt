package com.example.health.api.ai

import com.google.gson.annotations.SerializedName

data class GeneralResponse(
    val id: String,
    val choices: List<Choice>,
    val created: Long,
    val model: String,
    @SerializedName("object")
    val responseObject: String,
    val usage: Usage
)