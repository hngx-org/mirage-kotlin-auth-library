package com.shegs.hng_auth_library.ui.screens.repositories

import com.shegs.hng_auth_library.ui.screens.model.SignupRequest
import com.shegs.hng_auth_library.ui.screens.model.SignupResponse
import com.shegs.hng_auth_library.ui.screens.network.ApiResponse
import com.shegs.hng_auth_library.ui.screens.network.ApiService

class SignupRepository(private val apiService: ApiService) {
    suspend fun signup(signupRequest: SignupRequest): ApiResponse<SignupResponse> {
        return try {
            val response = apiService.signup(signupRequest)

            if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Error("Signup failed: ${response.message()}")
            }
        } catch (e: Exception) {
            ApiResponse.Error("Network error: ${e.message}")
        }
    }
}
