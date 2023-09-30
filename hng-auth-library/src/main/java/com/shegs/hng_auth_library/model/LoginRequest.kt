package com.shegs.hng_auth_library.model

data class LoginRequest(
    val email:String,
    val password:String,
)