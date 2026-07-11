package com.eparking.parqueapp.presentation.screens.reservas

import java.time.LocalDate
import java.time.LocalTime

/**
 * Estado de la interfaz para el formulario de reserva.
 */
data class FormReservaUiState(
    val fecha: LocalDate = LocalDate.now(),
    val horaInicio: LocalTime = LocalTime.of(9, 0),
    val horaFin: LocalTime = LocalTime.of(11, 0),
    val duracionHoras: Long = 2,
    val totalPagar: Double = 0.0
)
