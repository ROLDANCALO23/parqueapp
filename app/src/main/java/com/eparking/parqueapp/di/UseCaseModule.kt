package com.eparking.parqueapp.di

import com.eparking.parqueapp.domain.repository.ReservaRepository
import com.eparking.parqueapp.domain.usecase.*

/**
 * Módulo para proveer los casos de uso.
 */
class UseCaseModule(private val repository: ReservaRepository) {
    
    val reservaUseCases by lazy {
        ReservaUseCases(
            getReservas = GetReservasUseCase(repository),
            getReserva = GetReservaUseCase(repository),
            insertarReserva = InsertarReservaUseCase(repository),
            actualizarReserva = ActualizarReservaUseCase(repository),
            eliminarReserva = EliminarReservaUseCase(repository)
        )
    }
}
