package com.eparking.parqueapp.data.remote.dto

/**
 * DTO que refleja el campo "data" de la respuesta de la API de login.
 */
data class LoginResponse(
    val email: String,
    val nombre: String
)
