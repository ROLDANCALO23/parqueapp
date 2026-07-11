package com.eparking.parqueapp.di

import com.eparking.parqueapp.presentation.viewmodel.perfil.LoginViewModel
import com.eparking.parqueapp.presentation.viewmodel.perfil.RegistrationViewModel
import com.eparking.parqueapp.presentation.viewmodel.perfil.PerfilViewModel
import com.eparking.parqueapp.presentation.viewmodel.mapa.MapaViewModel
import com.eparking.parqueapp.presentation.viewmodel.reservas.FormReservaViewModel
import com.eparking.parqueapp.presentation.viewmodel.reservas.ReservasViewModel

/**
 * Contenedor de dependencias manual (Manual DI).
 */
class AppContainer {
    
    val loginViewModel by lazy {
        LoginViewModel()
    }

    val registrationViewModel by lazy {
        RegistrationViewModel()
    }

    val perfilViewModel by lazy {
        PerfilViewModel()
    }

    val mapaViewModel by lazy {
        MapaViewModel()
    }

    val formReservaViewModel by lazy {
        FormReservaViewModel()
    }

    val reservasViewModel by lazy {
        ReservasViewModel()
    }
}
