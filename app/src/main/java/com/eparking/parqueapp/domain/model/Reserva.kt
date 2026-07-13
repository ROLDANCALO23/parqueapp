package com.eparking.parqueapp.domain.model

/**
 * Modelo de negocio "limpio" para una Reserva.
 */
data class Reserva(
    val id: String,
    val nombreEstacionamiento: String,
    val direccion: String,
    val fecha: String,
    val hora: String,
    val total: Double,
    val estado: String
)
