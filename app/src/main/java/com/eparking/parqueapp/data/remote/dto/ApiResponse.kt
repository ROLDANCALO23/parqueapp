package com.eparking.parqueapp.data.remote.dto

/**
 * Envoltorio genérico para respuestas de la API.
 */
data class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val message: String? = null
)
