package com.example.jimbro.api

import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.util.concurrent.TimeUnit

val loggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
val cookieHandler: CookieHandler = CookieManager()
val client = OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).cookieJar(
    JavaNetCookieJar(cookieHandler)).connectTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl("https://jimbro-api-copy-t6krpojnuq-et.a.run.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()


class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}