package com.eparking.parqueapp.domain.repository

import com.eparking.parqueapp.domain.model.Reserva

/**
 * Contrato del repositorio para la entidad Reserva.
 */
interface ReservaRepository {
    suspend fun getReservas(): List<Reserva>
    suspend fun insertarReserva(reserva: Reserva): Boolean
    suspend fun getReservaById(id: String): Reserva?
    suspend fun actualizarReserva(reserva: Reserva): Boolean
    suspend fun eliminarReserva(id: String): Boolean
}
