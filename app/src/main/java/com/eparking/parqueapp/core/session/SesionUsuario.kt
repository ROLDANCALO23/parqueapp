package com.eparking.parqueapp.core.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UsuarioSesion(val email: String, val nombre: String)

/**
 * Sesión del usuario logueado, en memoria (se pierde al cerrar la app).
 * Se actualiza al hacer login y la observan las pantallas que necesiten
 * mostrar los datos del usuario actual (ej. Perfil).
 */
object SesionUsuario {
    private val _usuarioActual = MutableStateFlow<UsuarioSesion?>(null)
    val usuarioActual = _usuarioActual.asStateFlow()

    fun iniciarSesion(email: String, nombre: String) {
        _usuarioActual.value = UsuarioSesion(email, nombre)
    }
}
