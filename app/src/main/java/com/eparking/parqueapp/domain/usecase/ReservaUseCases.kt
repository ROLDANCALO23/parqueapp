package com.eparking.parqueapp.domain.usecase

/**
 * Agrupador de casos de uso para la entidad Reserva.
 */
data class ReservaUseCases(
    val getReservas: GetReservasUseCase,
    val getReserva: GetReservaUseCase,
    val insertarReserva: InsertarReservaUseCase,
    val actualizarReserva: ActualizarReservaUseCase,
    val eliminarReserva: EliminarReservaUseCase
)
