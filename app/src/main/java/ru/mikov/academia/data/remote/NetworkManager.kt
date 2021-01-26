package ru.mikov.academia.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.mikov.academia.AppConfig
import ru.mikov.academia.data.remote.interceptors.ApiKeyInterceptor

object NetworkManager {
    @ExperimentalSerializationApi
    val api: RestService by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val client = OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .addInterceptor(ApiKeyInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        retrofit.create(RestService::class.java)
    }
}