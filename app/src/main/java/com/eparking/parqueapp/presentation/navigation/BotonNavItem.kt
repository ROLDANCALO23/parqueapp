package com.eparking.parqueapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Sealed class que representa los elementos de navegación de la barra inferior (BottomBar).
 * 
 * 1. Jerarquía Restringida: Define un conjunto fijo de rutas permitidas para los botones del menú.
 * 2. Type Safety: Garantiza que solo manejemos destinos válidos para la barra inferior.
 * 3. Encapsulamiento: Cada objeto contiene su propia ruta, título e icono.
 * 
 * Se han excluido pantallas como 'Inicio' que no deben aparecer como botones en la barra.
 */
sealed class BotonNavItem (val ruta: String, val titulo: String, val icono: ImageVector) {
    data object Mapa: BotonNavItem(ruta = NavRutas.MAPA, titulo = "Mapa", icono = Icons.Default.Map)
    data object Reservas: BotonNavItem(ruta = NavRutas.RESERVAS, titulo = "Reservas", icono = Icons.Default.FormatListNumbered)
    data object Perfil: BotonNavItem(ruta = NavRutas.PERFIL, titulo = "Perfil", icono = Icons.Default.Person)
}
