package com.eparking.parqueapp.presentation.viewmodel.reservas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eparking.parqueapp.data.remote.RetrofitClient
import com.eparking.parqueapp.presentation.screens.reservas.ReservasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReservasViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ReservasUiState())
    val uiState = _uiState.asStateFlow()

    init {
        cargarReservas()
    }

    fun cargarReservas() {
        viewModelScope.launch {
            try {
                val reservasDto = RetrofitClient.reservasApiService.obtenerReservas()
                _uiState.value = ReservasUiState(
                    reservas = reservasDto.map { it.toReservaHistorial() }
                )
            } catch (e: Exception) {
                Log.e("ReservasViewModel", "Error al obtener reservas: ${e.message}", e)
                _uiState.value = ReservasUiState(reservas = emptyList())
            }
        }
    }
}
