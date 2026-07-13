package com.eparking.parqueapp.data.remote.dto

import com.eparking.parqueapp.presentation.screens.reservas.ReservaHistorial
import java.time.LocalDate
import java.time.LocalTime
import com.google.gson.annotations.SerializedName

/**
 * DTO que refleja EXACTAMENTE el JSON de la API de AWS.
 */
data class ReservaDto(
    val id: String,
    @SerializedName("nombreEstacionamiento")
    val nombreEstacionamiento: String,
    val direccion: String,
    val fecha: String,
    val hora: String,
    val total: Double,
    val estado: String
) {
    /**
     * Función de extensión para convertir a modelo de UI.
     */
    fun toReservaHistorial() = ReservaHistorial(
        id = id,
        nombreEstacionamiento = nombreEstacionamiento,
        direccion = direccion,
        fecha = try { LocalDate.parse(fecha) } catch(e: Exception) { LocalDate.now() },
        hora = try { LocalTime.parse(hora) } catch(e: Exception) { LocalTime.now() },
        total = total,
        estado = estado
    )
}
