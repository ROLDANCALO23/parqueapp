package com.eparking.parqueapp.presentation.viewmodel.mapa

import android.location.Location
import androidx.lifecycle.ViewModel
import com.eparking.parqueapp.presentation.screens.mapa.MapaUiState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Modelo de datos para un Estacionamiento.
 */
data class Estacionamiento(
    val id: String,
    val nombre: String,
    val direccion: String,
    val latitud: Double,
    val longitud: Double,
    val libres: Int,
    val precioHora: Double,
    val distanciaMetros: Float = 0f
)

class MapaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MapaUiState())
    val uiState = _uiState.asStateFlow()

    private val todosLosEstacionamientos = listOf(
        Estacionamiento("1", "E-PARKING Parque Kennedy", "Av. Diagonal & Schell", -12.1221, -77.0298, 15, 4.0),
        Estacionamiento("2", "Parqueo Central", "Jr. Huancavelica 251", -12.046374, -77.042793, 8, 5.5),
        Estacionamiento("3", "Cercado Parking", "Av. Abancay 450", -12.0549, -77.0361, 20, 3.0),
        Estacionamiento("4", "San Isidro Park", "Av. Rivera Navarrete", -12.0951, -77.0353, 5, 7.0),
        Estacionamiento("5", "Plaza Norte Parking", "Av. Tomas Valle", -11.9936, -77.0592, 45, 2.5)
    )

    fun onPermissionResult(isGranted: Boolean) {
        _uiState.update { it.copy(isPermissionGranted = isGranted) }
    }

    fun selectEstacionamiento(estacionamiento: Estacionamiento) {
        _uiState.update { it.copy(estacionamientoSeleccionado = estacionamiento) }
    }

    fun updateUserLocation(latLng: LatLng) {
        _uiState.update { it.copy(userLocation = latLng, isLoading = false) }
        filtrarYOrdenarEstacionamientos(latLng)
    }

    private fun filtrarYOrdenarEstacionamientos(userLoc: LatLng) {
        val resultados = mutableListOf<Estacionamiento>()

        todosLosEstacionamientos.forEach { est ->
            val distanceResults = FloatArray(1)
            Location.distanceBetween(
                userLoc.latitude, userLoc.longitude,
                est.latitud, est.longitud,
                distanceResults
            )
            val distance = distanceResults[0]

            if (distance <= 2000) {
                resultados.add(est.copy(distanciaMetros = distance))
            }
        }

        val ordenados = resultados.sortedBy { it.distanciaMetros }
        _uiState.update { it.copy(estacionamientosCercanos = ordenados) }
    }
}
