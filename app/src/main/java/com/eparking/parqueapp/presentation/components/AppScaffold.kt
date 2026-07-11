package com.eparking.parqueapp.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.eparking.parqueapp.presentation.event.EventBus
import com.eparking.parqueapp.presentation.event.UiEvent
import com.eparking.parqueapp.presentation.navigation.NavRutas
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppScaffold(navHostController: NavHostController,
                content: @Composable (PaddingValues) -> Unit) {
    
    // Obtenemos la ruta actual para decidir si mostrar la barra
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Configuración del Snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Observador del EventBus para mostrar los Snackbars
    LaunchedEffect(Unit) {
        EventBus.events.collectLatest { event ->
            when (event) {
                is UiEvent.Success -> {
                    snackbarHostState.showSnackbar(
                        SnackbarCustom(
                            message = event.mensaje,
                            status = SnackbarStatus.SUCCESS
                        )
                    )
                }
                is UiEvent.Error -> {
                    snackbarHostState.showSnackbar(
                        SnackbarCustom(
                            message = event.mensaje,
                            status = SnackbarStatus.ERROR
                        )
                    )
                }
                is UiEvent.Warning -> {
                    snackbarHostState.showSnackbar(
                        SnackbarCustom(
                            message = event.mensaje,
                            status = SnackbarStatus.WARNING
                        )
                    )
                }
            }
        }
    }

    // Definimos las rutas donde NO queremos mostrar la barra inferior
    val rutasSinBarra = listOf(
        NavRutas.INICIO,
        NavRutas.LOGIN,
        NavRutas.REGISTRO
    )

    Scaffold(
        snackbarHost = { 
            SnackbarHost(snackbarHostState) { data ->
                val custom = data.visuals as? SnackbarCustom
                if (custom != null) {
                    val status = custom.status
                    Snackbar(
                        containerColor = status.colorFondo,
                        contentColor = status.colorTexto,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = status.icono, contentDescription = "", tint = status.colorTexto)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(custom.message)
                        }
                    }
                } else {
                    Snackbar(data)
                }
            }
        },
        bottomBar = {
            // Solo mostramos la barra si la ruta actual NO está en la lista
            if (currentRoute !in rutasSinBarra) {
                AppBottomBar(navHostController)
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}
