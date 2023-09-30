package com.shegs.hng_auth_library.model

data class AuthResponse(
    val data: UserData,
    val message: String,
    val status: String? = ""
)

data class UserData(
    val created_at: String,
    val credit: Int,
    val email: String,
    val id: String,
    val name: String,
    val updated_at: String
)