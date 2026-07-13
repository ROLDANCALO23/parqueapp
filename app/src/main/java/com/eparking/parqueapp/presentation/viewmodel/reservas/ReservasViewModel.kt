package com.eparking.parqueapp.presentation.viewmodel.reservas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eparking.parqueapp.domain.usecase.ReservaUseCases
import com.eparking.parqueapp.presentation.screens.reservas.ReservaHistorial
import com.eparking.parqueapp.presentation.screens.reservas.ReservasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class ReservasViewModel(private val reservaUseCases: ReservaUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow(ReservasUiState())
    val uiState = _uiState.asStateFlow()

    init {
        cargarReservas()
    }

    fun cargarReservas() {
        viewModelScope.launch {
            Log.d("ReservasViewModel", "Iniciando carga de reservas...")
            _uiState.update { it.copy(isLoading = true) }
            try {
                // 1. Llamada al Caso de Uso
                val listaDominio = reservaUseCases.getReservas()
                Log.d("ReservasViewModel", "Datos recibidos del Dominio: ${listaDominio.size} elementos")
                
                _uiState.update { state ->
                    state.copy(
                        reservas = listaDominio.map { domain ->
                            // 2. Mapeo con LOGS para detectar fallos de formato
                            Log.d("ReservasViewModel", "Mapeando: ${domain.nombreEstacionamiento} | Fecha: ${domain.fecha} | Hora: ${domain.hora}")
                            
                            ReservaHistorial(
                                id = domain.id,
                                nombreEstacionamiento = domain.nombreEstacionamiento,
                                direccion = domain.direccion,
                                fecha = try { 
                                    LocalDate.parse(domain.fecha) 
                                } catch(e:Exception) { 
                                    Log.e("ReservasViewModel", "Error parseando fecha: ${domain.fecha}")
                                    LocalDate.now() 
                                },
                                hora = try { 
                                    LocalTime.parse(domain.hora) 
                                } catch(e:Exception) { 
                                    Log.e("ReservasViewModel", "Error parseando hora: ${domain.hora}")
                                    LocalTime.now() 
                                },
                                total = domain.total,
                                estado = domain.estado
                            )
                        },
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Log.e("ReservasViewModel", "ERROR CRÍTICO: ${e.message}", e)
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }
}
