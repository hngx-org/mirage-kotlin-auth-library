package com.shegs.hng_auth_library.repositories

import com.shegs.hng_auth_library.model.AuthResponse
import com.shegs.hng_auth_library.model.LogoutResponse
import com.shegs.hng_auth_library.network.ApiResponse
import com.shegs.hng_auth_library.network.ApiService

class ProfileRepository(
    private val apiService: ApiService,
) {
    suspend fun profile(): ApiResponse<AuthResponse> {
        return try {
            val response = apiService.profile()

            if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Error(response.message())
            }
        } catch (e: Exception) {
            ApiResponse.Error("Network error: ${e.message}")
        }
    }

    suspend fun logout(): ApiResponse<LogoutResponse> {
        return try {
            val response = apiService.logout()

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