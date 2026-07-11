package com.eparking.parqueapp.presentation.viewmodel.reservas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eparking.parqueapp.presentation.event.EventBus
import com.eparking.parqueapp.presentation.event.UiEvent
import com.eparking.parqueapp.presentation.screens.reservas.FormReservaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.Duration

class FormReservaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FormReservaUiState())
    val uiState = _uiState.asStateFlow()

    fun initReserva(precioPorHora: Double) {
        _uiState.update { 
            it.copy(totalPagar = it.duracionHoras * precioPorHora)
        }
    }

    fun onFechaChange(nuevaFecha: LocalDate) {
        _uiState.update { it.copy(fecha = nuevaFecha) }
    }

    fun onHoraInicioChange(nuevaHora: LocalTime, precioPorHora: Double) {
        _uiState.update { 
            val duracion = calcularDuracion(nuevaHora, it.horaFin)
            it.copy(
                horaInicio = nuevaHora,
                duracionHoras = duracion,
                totalPagar = duracion * precioPorHora
            )
        }
    }

    fun onHoraFinChange(nuevaHora: LocalTime, precioPorHora: Double) {
        _uiState.update { 
            val duracion = calcularDuracion(it.horaInicio, nuevaHora)
            it.copy(
                horaFin = nuevaHora,
                duracionHoras = duracion,
                totalPagar = duracion * precioPorHora
            )
        }
    }

    private fun calcularDuracion(inicio: LocalTime, fin: LocalTime): Long {
        val duration = Duration.between(inicio, fin)
        val horas = duration.toHours()
        return if (horas > 0) horas else 0
    }

    fun confirmarReserva(estacionamientoId: String) {
        viewModelScope.launch {
            if (!validarReserva()) {
                return@launch
            }

            val data = _uiState.value
            println("Enviando reserva al servidor para el ID: $estacionamientoId")
            println("Detalles: ${data.fecha} de ${data.horaInicio} a ${data.horaFin}")

            EventBus.send(UiEvent.Success("¡Reserva confirmada exitosamente!"))
        }
    }

    private suspend fun validarReserva(): Boolean {
        val state = _uiState.value
        return when {
            state.fecha.isBefore(LocalDate.now()) -> {
                EventBus.send(UiEvent.Warning("La fecha no puede ser anterior a hoy"))
                false
            }
            state.duracionHoras <= 0 -> {
                EventBus.send(UiEvent.Warning("La hora de salida debe ser posterior a la de entrada"))
                false
            }
            else -> true
        }
    }
}
