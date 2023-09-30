package com.shegs.hng_auth_library.ui.screens.authlibrary

import com.shegs.hng_auth_library.ui.screens.network.ApiService
import com.shegs.hng_auth_library.ui.screens.repositories.SignupRepository

object AuthLibrary {
    fun createAuthService(): ApiService {
        return ApiService.instance
    }

    fun createSignupRepository(apiService: ApiService): SignupRepository {
        return SignupRepository(apiService)
    }
}