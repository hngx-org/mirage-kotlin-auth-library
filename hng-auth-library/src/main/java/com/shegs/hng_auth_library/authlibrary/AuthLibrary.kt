package com.shegs.hng_auth_library.authlibrary

import com.shegs.hng_auth_library.network.ApiService
import com.shegs.hng_auth_library.repositories.SignupRepository

object AuthLibrary {
    fun createAuthService(): ApiService {
        return ApiService.instance
    }

    fun createSignupRepository(apiService: ApiService): SignupRepository {
        return SignupRepository(apiService)
    }
}