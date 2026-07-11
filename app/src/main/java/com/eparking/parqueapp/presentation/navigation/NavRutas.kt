package com.eparking.parqueapp.presentation.navigation

object NavRutas {
    const val INICIO = "inicio"
    const val MAPA = "mapa"
    const val PERFIL = "perfil"
    const val RESERVAS = "reservas"
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val FORM_RESERVA = "form_reserva"

    /**
     * Lista de elementos que aparecerán en la barra de navegación inferior.
     * Solo incluimos Mapa, Reservas y Perfil.
     */
    val items = listOf(
        BotonNavItem.Mapa,
        BotonNavItem.Reservas,
        BotonNavItem.Perfil
    )
}
