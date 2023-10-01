package com.shegs.hng_auth_library.authlibrary

import android.content.Context
import com.shegs.hng_auth_library.network.ApiService
import com.shegs.hng_auth_library.repositories.DataStoreRepository
import com.shegs.hng_auth_library.repositories.LoginRepository
import com.shegs.hng_auth_library.repositories.LogoutRepository
import com.shegs.hng_auth_library.repositories.ProfileRepository
import com.shegs.hng_auth_library.repositories.SignupRepository

object AuthLibrary {
    fun createAuthService(): ApiService {
        return ApiService.instance
    }

    fun createDataStoreRepository(context: Context): DataStoreRepository {
        return DataStoreRepository(context)
    }

    fun createSignupRepository(apiService: ApiService): SignupRepository {
        return SignupRepository(apiService)
    }

    fun createLoginRepository(
        apiService: ApiService,
        dataStoreRepository: DataStoreRepository
    ): LoginRepository {
        return LoginRepository(apiService, dataStoreRepository)
    }

    fun createProfileRepository(
        apiService: ApiService,
    ) : ProfileRepository {
        return ProfileRepository(apiService)
    }

    fun createLogoutRepository(
        apiService: ApiService,
    ) : LogoutRepository {
        return LogoutRepository(apiService)
    }

}