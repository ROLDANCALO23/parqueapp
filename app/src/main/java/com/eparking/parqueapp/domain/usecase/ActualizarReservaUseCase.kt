package com.eparking.parqueapp.domain.usecase

import com.eparking.parqueapp.domain.model.Reserva
import com.eparking.parqueapp.domain.repository.ReservaRepository

class ActualizarReservaUseCase(private val repository: ReservaRepository) {
    suspend operator fun invoke(reserva: Reserva) = repository.actualizarReserva(reserva)
}
