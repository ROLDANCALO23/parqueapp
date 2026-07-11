package com.eparking.parqueapp.presentation.screens.mapa

import com.eparking.parqueapp.presentation.viewmodel.mapa.Estacionamiento
import com.google.android.gms.maps.model.LatLng

/**
 * Estado de la interfaz para la pantalla de Mapa.
 */
data class MapaUiState(
    val userLocation: LatLng? = null,
    val estacionamientosCercanos: List<Estacionamiento> = emptyList(),
    val estacionamientoSeleccionado: Estacionamiento? = null,
    val isPermissionGranted: Boolean = false,
    val isLoading: Boolean = true
)
