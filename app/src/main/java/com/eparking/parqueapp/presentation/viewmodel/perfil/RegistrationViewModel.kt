package com.eparking.parqueapp.presentation.viewmodel.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eparking.parqueapp.data.remote.datasource.RemoteDataSource
import com.eparking.parqueapp.presentation.event.EventBus
import com.eparking.parqueapp.presentation.event.UiEvent
import com.eparking.parqueapp.presentation.screens.perfil.RegistrationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class RegistrationViewModel(private val remoteDataSource: RemoteDataSource) : ViewModel() {
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

    fun onRegistroClick() {
        viewModelScope.launch {
            if (!validarFormulario()) {
                return@launch
            }

            val data = _uiState.value
            
            try {
                val response = remoteDataSource.registrarUsuario(
                    data.nombre,
                    data.email,
                    data.telefono,
                    data.password
                )

                if (response.isSuccessful) {
                    EventBus.send(UiEvent.Success("¡Registro completado con éxito!"))
                    _uiState.value = _uiState.value.copy(registroExitoso = true)
                } else {
                    val errorBody = response.errorBody()?.string()
                    println("Error en Registro: $errorBody")
                    EventBus.send(UiEvent.Error("Error al registrar: ${response.code()}"))
                }
            } catch (e: IOException) {
                EventBus.send(UiEvent.Error("No se pudo conectar al servidor"))
            } catch (e: Exception) {
                EventBus.send(UiEvent.Error("Ocurrió un error inesperado"))
            }
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
            else -> true
        }
    }
}
