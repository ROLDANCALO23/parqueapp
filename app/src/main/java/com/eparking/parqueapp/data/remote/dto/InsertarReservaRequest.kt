package com.eparking.parqueapp.data.remote.dto

/**
 * Cuerpo (Body) para peticiones POST/PUT.
 */
data class InsertarReservaRequest(
    val nombre: String,
    val direccion: String,
    val fecha: String,
    val hora: String,
    val total: Double,
    val estado: String
)
