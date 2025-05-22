package com.example.health.server

import android.content.Context
import com.example.health.api.ai.MistralApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)
        val requestBuilder = chain.request().newBuilder()
        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(requestBuilder.build())
    }
}

class MistralAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer Ps9HteRpwJT2sVPNjTWo0VHCAZ8PmpcV")
        return chain.proceed(requestBuilder.build())
    }
}

object WebServerSingleton {

    fun getMistralApiService(context: Context): MistralApiService {
        val mistralAuthInterceptor = MistralAuthInterceptor()
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(mistralAuthInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS) // Увеличиваем время ожидания подключения
            .readTimeout(30, TimeUnit.SECONDS)    // Увеличиваем время ожидания чтения
            .writeTimeout(30, TimeUnit.SECONDS)   // Увеличиваем время ожидания записи
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mistral.ai/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MistralApiService::class.java)
    }
}