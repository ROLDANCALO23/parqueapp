package com.eparking.parqueapp.di

import com.eparking.parqueapp.presentation.viewmodel.perfil.LoginViewModel
import com.eparking.parqueapp.presentation.viewmodel.perfil.RegistrationViewModel
import com.eparking.parqueapp.presentation.viewmodel.perfil.PerfilViewModel
import com.eparking.parqueapp.presentation.viewmodel.mapa.MapaViewModel
import com.eparking.parqueapp.presentation.viewmodel.reservas.FormReservaViewModel
import com.eparking.parqueapp.presentation.viewmodel.reservas.ReservasViewModel

/**
 * Contenedor de dependencias manual (Manual DI).
 * Organiza todos los módulos del proyecto.
 */
class AppContainer {
    
    // 1. Capa Core / Network
    private val networkModule by lazy { NetworkModule() }
    
    // 2. Capa Data / Repository
    private val repositoryModule by lazy { 
        RepositoryModule(networkModule.apiService) 
    }
    
    // 3. Capa Domain / UseCases
    val useCaseModule by lazy { 
        UseCaseModule(repositoryModule.reservaRepository) 
    }

    // 4. Capa Presentation / ViewModels
    val loginViewModel by lazy { LoginViewModel(repositoryModule.remoteDataSource) }

    val registrationViewModel by lazy { RegistrationViewModel(repositoryModule.remoteDataSource) }

    val perfilViewModel by lazy { PerfilViewModel() }

    val mapaViewModel by lazy { MapaViewModel() }

    val formReservaViewModel by lazy { FormReservaViewModel() }

    val reservasViewModel by lazy { 
        // Inyectamos los casos de uso en el ViewModel
        ReservasViewModel(useCaseModule.reservaUseCases) 
    }
}
