package com.eparking.parqueapp.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.eparking.parqueapp.presentation.navigation.NavRutas

@Composable
fun AppBottomBar(navHostController: NavHostController){
    // Usamos la lista de rutas que definimos en NavRutas
    val opciones = NavRutas.items
    
    NavigationBar {
        // Obtenemos la ruta actual para saber qué botón marcar como seleccionado
        val navBackStackEntry = navHostController.currentBackStackEntryAsState().value
        val rutaSel = navBackStackEntry?.destination?.route
        
        opciones.forEach { item ->
            NavigationBarItem(
                selected = rutaSel == item.ruta,
                onClick = { navHostController.navigate(item.ruta) },
                icon = { Icon(item.icono, contentDescription = item.titulo) },
                label = { Text(item.titulo) }
            )
        }
    }
}
