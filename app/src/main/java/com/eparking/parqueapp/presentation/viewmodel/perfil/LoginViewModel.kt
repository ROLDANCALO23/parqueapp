package com.eparking.parqueapp.presentation.viewmodel.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eparking.parqueapp.presentation.event.EventBus
import com.eparking.parqueapp.presentation.event.UiEvent
import com.eparking.parqueapp.presentation.screens.perfil.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(valor: String) {
        _uiState.value = _uiState.value.copy(email = valor)
    }

    fun onPasswordChange(valor: String) {
        _uiState.value = _uiState.value.copy(password = valor)
    }

    fun onLoginClick() {
        viewModelScope.launch {
            if (!validarFormulario()) {
                return@launch
            }

            // Simulación de éxito
            println("Iniciando sesión con: ${_uiState.value.email}")
            EventBus.send(UiEvent.Success("¡Bienvenido a ParqueApp!"))
        }
    }

    private suspend fun validarFormulario(): Boolean {
        val state = _uiState.value
        return when {
            state.email.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese su correo electrónico"))
                false
            }
            state.password.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese su contraseña"))
                false
            }
            else -> true
        }
    }
}
