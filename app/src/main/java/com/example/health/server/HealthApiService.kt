package com.example.health.server

import com.example.health.model.ChangeEmailRequest
import com.example.health.model.ChangePasswordRequest
import com.example.health.model.UserRegisterRequest
import com.example.health.model.UserLoginRequest
import com.example.health.model.HealthParameterEntry
import com.example.health.model.RegisterResponseRemote
import com.example.health.model.UserLoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Response

interface HealthApiService {

    @POST("register")
    suspend fun registerUser(@Body request: UserRegisterRequest): RegisterResponseRemote


    @POST("/login")
    suspend fun loginUser(@Body request: UserLoginRequest): UserLoginResponse

    @POST("parameters/{userId}/{paramId}")
    suspend fun addParameterEntry(
        @Path("userId") userId: String,
        @Path("paramId") paramId: Int,
        @Body entry: Map<String, String>
    )

    @GET("parameters/{userId}/{paramId}")
    suspend fun getParameterEntries(
        @Path("userId") userId: String,
        @Path("paramId") paramId: Int
    ): List<HealthParameterEntry>

    @POST("change-email")
    suspend fun changeEmail(@Body request: ChangeEmailRequest): Response<Unit>

    @POST("change-password")
    suspend fun changePassword(@Body request: ChangePasswordRequest): Response<Unit>
}