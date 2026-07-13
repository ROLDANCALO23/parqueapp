package com.eparking.parqueapp.data.remote.dto

/**
 * Envoltorio para lista de reservas si la API devuelve un objeto data.
 */
data class ReservasDataDto(
    val reservas: List<ReservaDto>
)
