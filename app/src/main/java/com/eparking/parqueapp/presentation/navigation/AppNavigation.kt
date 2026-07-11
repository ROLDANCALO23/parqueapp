package com.eparking.parqueapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import com.eparking.parqueapp.di.AppContainer
import com.eparking.parqueapp.presentation.screens.inicio.InicioScreen
import com.eparking.parqueapp.presentation.screens.mapa.MapaScreen
import com.eparking.parqueapp.presentation.screens.perfil.PerfilScreen
import com.eparking.parqueapp.presentation.screens.perfil.LoginScreen
import com.eparking.parqueapp.presentation.screens.perfil.RegistrationScreen
import com.eparking.parqueapp.presentation.screens.reservas.FormReservaScreen
import com.eparking.parqueapp.presentation.screens.reservas.ReservasScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController, 
    container: AppContainer,
    paddingValues: PaddingValues
){
    NavHost(
        navController = navHostController,
        startDestination = NavRutas.INICIO,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(NavRutas.INICIO) {
            InicioScreen(navHostController)
        }
        composable(route = NavRutas.MAPA) {
            MapaScreen(container.mapaViewModel, navHostController)
        }
        composable(NavRutas.PERFIL) {
            PerfilScreen(container.perfilViewModel)
        }
        composable(NavRutas.RESERVAS) {
            ReservasScreen(container.reservasViewModel)
        }
        composable(NavRutas.LOGIN) {
            LoginScreen(container.loginViewModel, navHostController)
        }
        composable(NavRutas.REGISTRO) {
            RegistrationScreen(container.registrationViewModel, navHostController)
        }
        composable(NavRutas.FORM_RESERVA) {
            FormReservaScreen(
                container.mapaViewModel, 
                container.formReservaViewModel,
                navHostController
            )
        }
    }
}
