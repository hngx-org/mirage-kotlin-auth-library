package com.shegs.hng_auth_library.ui.screens.network

import com.shegs.hng_auth_library.ui.screens.model.SignupRequest
import com.shegs.hng_auth_library.ui.screens.model.SignupResponse
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
    suspend fun signup(@Body signupRequest: SignupRequest): Response<SignupResponse>

}