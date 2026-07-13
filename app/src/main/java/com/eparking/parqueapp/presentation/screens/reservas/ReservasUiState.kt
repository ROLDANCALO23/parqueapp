package com.eparking.parqueapp.presentation.screens.reservas

import java.time.LocalDate
import java.time.LocalTime

/**
 * Modelo para representar una Reserva individual.
 */
data class ReservaHistorial(
    val id: String,
    val nombreEstacionamiento: String,
    val direccion: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val total: Double,
    val estado: String // "PRÓXIMA", "COMPLETADA"
)

/**
 * Estado de la interfaz para la pantalla de Historial de Reservas.
 */
data class ReservasUiState(
    val reservas: List<ReservaHistorial> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
