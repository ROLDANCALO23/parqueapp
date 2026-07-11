package com.eparking.parqueapp.presentation.viewmodel.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eparking.parqueapp.presentation.event.EventBus
import com.eparking.parqueapp.presentation.event.UiEvent
import com.eparking.parqueapp.presentation.screens.perfil.PerfilUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PerfilViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PerfilUiState())
    val uiState = _uiState.asStateFlow()

    fun onNombreChange(valor: String) {
        _uiState.value = _uiState.value.copy(nombre = valor)
    }

    fun onTelefonoChange(valor: String) {
        _uiState.value = _uiState.value.copy(telefono = valor)
    }

    fun onEmailChange(valor: String) {
        _uiState.value = _uiState.value.copy(email = valor)
    }

    fun onGuardarCambios() {
        viewModelScope.launch {
            if (!validarFormulario()) {
                return@launch
            }

            // Simulación de éxito
            println("Cambios guardados para: ${_uiState.value.nombre}")
            EventBus.send(UiEvent.Success("Perfil actualizado correctamente"))
        }
    }

    private suspend fun validarFormulario(): Boolean {
        val state = _uiState.value
        return when {
            state.nombre.isBlank() -> {
                EventBus.send(UiEvent.Warning("El nombre no puede estar vacío"))
                false
            }
            state.telefono.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese su número de celular"))
                false
            }
            state.email.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese su correo electrónico"))
                false
            }
            else -> true
        }
    }
}
