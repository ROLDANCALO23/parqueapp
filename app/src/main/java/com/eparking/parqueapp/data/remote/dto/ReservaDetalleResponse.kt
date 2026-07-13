package com.eparking.parqueapp.data.remote.dto

/**
 * Respuesta específica para un solo elemento.
 */
data class ReservaDetalleResponse(
    val success: Boolean,
    val data: ReservaDto
)
