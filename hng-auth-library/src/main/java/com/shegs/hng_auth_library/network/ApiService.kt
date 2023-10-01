package com.shegs.hng_auth_library.network

import com.shegs.hng_auth_library.model.AuthResponse
import com.shegs.hng_auth_library.model.LoginRequest
import com.shegs.hng_auth_library.model.SignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    companion object {
        val instance: ApiService by lazy {
            val retrofit = RetrofitClient.create()
            retrofit.create(ApiService::class.java)
        }
    }

    @POST("api/auth/register")
    suspend fun signup(@Body signupRequest: SignupRequest): Response<AuthResponse>

    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<AuthResponse>


}