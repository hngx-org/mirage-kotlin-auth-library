package com.shegs.hng_auth_library.repositories

import com.shegs.hng_auth_library.model.LogoutResponse
import com.shegs.hng_auth_library.network.ApiResponse
import com.shegs.hng_auth_library.network.ApiService

class LogoutRepository(
    private val apiService: ApiService,
) {

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