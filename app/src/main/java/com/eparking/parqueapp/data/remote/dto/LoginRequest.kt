package com.eparking.parqueapp.data.remote.dto

/**
 * Cuerpo (Body) para la petición de login.
 */
data class LoginRequest(
    val email: String,
    val password: String
)
