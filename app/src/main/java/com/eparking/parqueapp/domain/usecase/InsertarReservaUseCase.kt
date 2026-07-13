package com.eparking.parqueapp.domain.usecase

import com.eparking.parqueapp.domain.model.Reserva
import com.eparking.parqueapp.domain.repository.ReservaRepository

class InsertarReservaUseCase(private val repository: ReservaRepository) {
    suspend operator fun invoke(reserva: Reserva) = repository.insertarReserva(reserva)
}
