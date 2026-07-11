package com.eparking.parqueapp.presentation.viewmodel.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eparking.parqueapp.presentation.event.EventBus
import com.eparking.parqueapp.presentation.event.UiEvent
import com.eparking.parqueapp.presentation.screens.perfil.RegistrationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState = _uiState.asStateFlow()

    fun onNombreChange(valor: String) {
        _uiState.value = _uiState.value.copy(nombre = valor)
    }

    fun onEmailChange(valor: String) {
        _uiState.value = _uiState.value.copy(email = valor)
    }

    fun onTelefonoChange(valor: String) {
        _uiState.value = _uiState.value.copy(telefono = valor)
    }

    fun onPasswordChange(valor: String) {
        _uiState.value = _uiState.value.copy(password = valor)
    }

    fun onConfirmPasswordChange(valor: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = valor)
    }

    fun onRegistroClick() {
        viewModelScope.launch {
            if (!validarFormulario()) {
                return@launch
            }
            
            val data = _uiState.value
            println("Registrando usuario: ${data.nombre} con teléfono ${data.telefono}")
            EventBus.send(UiEvent.Success("¡Registro completado con éxito!"))
        }
    }

    private suspend fun validarFormulario(): Boolean {
        val state = _uiState.value
        return when {
            state.nombre.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese su nombre completo"))
                false
            }
            state.email.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese su correo electrónico"))
                false
            }
            state.telefono.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese su número de celular"))
                false
            }
            state.password.isBlank() -> {
                EventBus.send(UiEvent.Warning("Ingrese una contraseña"))
                false
            }
            state.password != state.confirmPassword -> {
                EventBus.send(UiEvent.Warning("Las contraseñas no coinciden"))
                false
            }
            else -> true
        }
    }
}
