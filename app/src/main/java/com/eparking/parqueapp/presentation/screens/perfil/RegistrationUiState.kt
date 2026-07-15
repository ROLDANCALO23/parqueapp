package com.eparking.parqueapp.presentation.screens.perfil

/**
 * Estado de la interfaz para la pantalla de Registro.
 */
data class RegistrationUiState(
    val nombre: String = "",
    val email: String = "",
    val telefono: String = "",
    val password: String = "",
    val registroExitoso: Boolean = false
)
