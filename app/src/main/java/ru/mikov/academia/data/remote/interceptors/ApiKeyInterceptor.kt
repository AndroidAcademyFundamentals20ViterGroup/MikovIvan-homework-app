package ru.mikov.academia.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.mikov.academia.AppConfig.API_KEY
import ru.mikov.academia.AppConfig.API_KEY_HEADER


class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY_HEADER, API_KEY)
            .build()

        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}