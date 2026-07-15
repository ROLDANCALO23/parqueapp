package com.eparking.parqueapp.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Modelo para la petición de registro de usuario.
 * Debe coincidir con lo que espera tu Lambda en AWS.
 */
data class RegisterRequest(
    val nombre: String,
    @SerializedName("correo")
    val email: String,
    val telefono: String,
    val password: String
)
