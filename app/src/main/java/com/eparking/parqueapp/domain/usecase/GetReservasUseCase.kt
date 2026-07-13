package com.eparking.parqueapp.domain.usecase

import com.eparking.parqueapp.domain.repository.ReservaRepository

class GetReservasUseCase(private val repository: ReservaRepository) {
    suspend operator fun invoke() = repository.getReservas()
}
