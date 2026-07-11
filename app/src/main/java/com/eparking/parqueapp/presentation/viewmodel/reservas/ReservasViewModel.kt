package com.eparking.parqueapp.presentation.viewmodel.reservas

import androidx.lifecycle.ViewModel
import com.eparking.parqueapp.presentation.screens.reservas.ReservaHistorial
import com.eparking.parqueapp.presentation.screens.reservas.ReservasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.LocalTime

class ReservasViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ReservasUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = ReservasUiState(
            reservas = listOf(
                ReservaHistorial("1", "Parque Kennedy", "Miraflores", LocalDate.of(2023, 11, 15), LocalTime.of(14, 0), 8.0, "PRÓXIMA"),
                ReservaHistorial("2", "E-Parking Central", "Av. Central 450", LocalDate.of(2023, 11, 10), LocalTime.of(9, 30), 12.0, "COMPLETADA"),
                ReservaHistorial("3", "Larcomar", "Malecón de la Reserva", LocalDate.of(2023, 11, 5), LocalTime.of(18, 20), 15.50, "COMPLETADA")
            )
        )
    }
}
