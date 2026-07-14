package com.eparking.parqueapp.presentation.viewmodel.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eparking.parqueapp.core.session.SesionUsuario
import com.eparking.parqueapp.data.remote.datasource.RemoteDataSource
import com.eparking.parqueapp.presentation.event.EventBus
import com.eparking.parqueapp.presentation.event.UiEvent
import com.eparking.parqueapp.presentation.screens.perfil.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel(private val remoteDataSource: RemoteDataSource) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    // ponytail: fallback offline hardcodeado solo para este usuario de demo (Ricardo Mendoza),
    // exclusivamente cuando la API no responde (IOException). No es un mecanismo de autenticación
    // real: las credenciales quedan visibles en el APK. Si se necesita offline para cualquier
    // usuario, el upgrade sería cachear el último login exitoso en vez de hardcodear credenciales.
    private companion object {
        const val OFFLINE_EMAIL = "ricardo.mendoza@email.com"
        const val OFFLINE_PASSWORD = "Ricardo2026!"
        const val OFFLINE_NOMBRE = "Ricardo Mendoza"
    }

    fun onEmailChange(valor: String) {
        _uiState.value = _uiState.value.copy(email = valor)
    }

    fun onPasswordChange(valor: String) {
        _uiState.value = _uiState.value.copy(password = valor)
    }

    fun onNavegacionConsumida() {
        _uiState.value = _uiState.value.copy(loginExitoso = false)
    }

    fun onLoginClick() {
        viewModelScope.launch {
            if (!validarFormulario()) {
                return@launch
            }

            val state = _uiState.value

            try {
                val response = remoteDataSource.login(state.email, state.password)
                val usuario = response.body()?.data

                if (response.isSuccessful && usuario != null) {
                    SesionUsuario.iniciarSesion(email = usuario.email, nombre = usuario.nombre)
                    EventBus.send(UiEvent.Success("¡Bienvenido ${usuario.nombre}!"))
                    _uiState.value = _uiState.value.copy(loginExitoso = true)
                } else {
                    EventBus.send(UiEvent.Warning("Correo o contraseña incorrectos"))
                }
            } catch (e: IOException) {
                if (state.email.equals(OFFLINE_EMAIL, ignoreCase = true) && state.password == OFFLINE_PASSWORD) {
                    SesionUsuario.iniciarSesion(email = OFFLINE_EMAIL, nombre = OFFLINE_NOMBRE)
                    _uiState.value = _uiState.value.copy(loginExitoso = true)
                } else {
                    EventBus.send(UiEvent.Error("No se pudo conectar. Verifica tu conexión."))
                }
            }
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
