package com.shegs.hng_auth_library.ui.screens.model

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirm_password: String
)
