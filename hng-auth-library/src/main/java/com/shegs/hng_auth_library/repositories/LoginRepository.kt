package com.shegs.hng_auth_library.repositories

import com.shegs.hng_auth_library.model.AuthResponse
import com.shegs.hng_auth_library.model.LoginRequest
import com.shegs.hng_auth_library.network.ApiResponse
import com.shegs.hng_auth_library.network.ApiService

class LoginRepository(
    private val apiService: ApiService
) {
    suspend fun login(loginRequest: LoginRequest): ApiResponse<AuthResponse> {
        return try {
            val response = apiService.login(loginRequest)

            if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Error(response.message())
            }
        } catch (e: Exception) {
            ApiResponse.Error("Network error: ${e.message}")
        }
    }

}