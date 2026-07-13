package com.eparking.parqueapp.data.repository

import com.eparking.parqueapp.data.remote.datasource.RemoteDataSource
import com.eparking.parqueapp.domain.model.Reserva
import com.eparking.parqueapp.domain.repository.ReservaRepository

/**
 * Implementación del repositorio. 
 * Convierte de DTO a Dominio para mantener la arquitectura limpia.
 */
class ReservaRepositoryImpl(private val remote: RemoteDataSource) : ReservaRepository {
    
    override suspend fun getReservas(): List<Reserva> {
        return remote.getReservas().map { dto ->
            Reserva(
                id = dto.id,
                nombreEstacionamiento = dto.nombreEstacionamiento,
                direccion = dto.direccion,
                fecha = dto.fecha,
                hora = dto.hora,
                total = dto.total,
                estado = dto.estado
            )
        }
    }

    override suspend fun insertarReserva(reserva: Reserva): Boolean {
        // Por implementar según necesidad
        return true
    }

    override suspend fun getReservaById(id: String): Reserva? {
        return null
    }

    override suspend fun actualizarReserva(reserva: Reserva): Boolean {
        return true
    }

    override suspend fun eliminarReserva(id: String): Boolean {
        return true
    }
}
