package com.example.health.server

import com.example.health.model.UserRegisterRequest
import com.example.health.model.UserLoginRequest
import com.example.health.model.HealthParameterEntry
import com.example.health.model.RegisterResponseRemote
import com.example.health.model.UserLoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HealthApiService {

    @POST("register")
    suspend fun registerUser(@Body request: UserRegisterRequest): RegisterResponseRemote


    @POST("/login")
    suspend fun loginUser(@Body request: UserLoginRequest): UserLoginResponse

    @POST("parameters/{userId}/{paramId}")
    suspend fun addParameterEntry(
        @Path("userId") userId: String,
        @Path("paramId") paramId: Int,
        @Body entry: HealthParameterEntry
    )

    @GET("parameters/{userId}/{paramId}")
    suspend fun getParameterEntries(
        @Path("userId") userId: String,
        @Path("paramId") paramId: Int
    ): List<HealthParameterEntry>
}
