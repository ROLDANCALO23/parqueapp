package com.eparking.parqueapp.domain.usecase

import com.eparking.parqueapp.domain.repository.ReservaRepository

class EliminarReservaUseCase(private val repository: ReservaRepository) {
    suspend operator fun invoke(id: String) = repository.eliminarReserva(id)
}
