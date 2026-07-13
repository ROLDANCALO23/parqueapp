package com.eparking.parqueapp.data.mapper

import com.eparking.parqueapp.data.remote.dto.InsertarReservaRequest
import com.eparking.parqueapp.data.remote.dto.ReservaDto
import com.eparking.parqueapp.domain.model.Reserva

/**
 * Traductor entre capas (DTO <-> Domain).
 */
object ReservaMapper {
    
    fun ReservaDto.toDomain(): Reserva {
        return Reserva(
            id = id,
            nombreEstacionamiento = nombreEstacionamiento,
            direccion = direccion,
            fecha = fecha,
            hora = hora,
            total = total,
            estado = estado
        )
    }

    fun Reserva.toRequest(): InsertarReservaRequest {
        return InsertarReservaRequest(
            nombre = nombreEstacionamiento,
            direccion = direccion,
            fecha = fecha,
            hora = hora,
            total = total,
            estado = estado
        )
    }
}
