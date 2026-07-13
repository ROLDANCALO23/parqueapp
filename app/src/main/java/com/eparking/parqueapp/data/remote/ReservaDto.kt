package com.eparking.parqueapp.data.remote

import com.eparking.parqueapp.presentation.screens.reservas.ReservaHistorial
import java.time.LocalDate
import java.time.LocalTime

data class ReservaDto(
    val id: String,
    val nombreEstacionamiento: String,
    val direccion: String,
    val fecha: String,   // "2026-07-15"
    val hora: String,    // "14:00"
    val total: Double,
    val estado: String   // "PRÓXIMA", "COMPLETADA"
) {

    fun toReservaHistorial(): ReservaHistorial {
        return ReservaHistorial(
            id = id,
            nombreEstacionamiento = nombreEstacionamiento,
            direccion = direccion,
            fecha = LocalDate.parse(fecha),
            hora = LocalTime.parse(hora),
            total = total,
            estado = estado
        )
    }
}