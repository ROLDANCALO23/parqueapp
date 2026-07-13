package com.eparking.parqueapp.domain.usecase

import com.eparking.parqueapp.domain.repository.ReservaRepository

class GetReservaUseCase(private val repository: ReservaRepository) {
    suspend operator fun invoke(id: String) = repository.getReservaById(id)
}
