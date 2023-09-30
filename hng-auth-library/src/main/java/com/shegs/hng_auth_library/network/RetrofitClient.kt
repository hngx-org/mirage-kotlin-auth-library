package com.shegs.hng_auth_library.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://spitfire-openai.onrender.com/"
    private val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
    private val okHttpClient = OkHttpClient.Builder()
        .cookieJar(cookieJar)
        .build()



    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}