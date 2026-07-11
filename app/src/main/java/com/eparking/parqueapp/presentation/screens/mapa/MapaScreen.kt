package com.eparking.parqueapp.presentation.screens.mapa

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.eparking.parqueapp.presentation.components.EstacionamientosBottomSheet
import com.eparking.parqueapp.presentation.navigation.NavRutas
import com.eparking.parqueapp.presentation.viewmodel.mapa.MapaViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapaScreen(viewModel: MapaViewModel, navController: androidx.navigation.NavHostController) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    
    // Estado de permisos
    var hasLocationPermission by remember { 
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasLocationPermission = isGranted
        viewModel.onPermissionResult(isGranted)
    }

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let {
                    viewModel.updateUserLocation(LatLng(it.latitude, it.longitude))
                }
            }
        }
    }

    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            startLocationUpdates(fusedLocationClient, locationCallback)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        if (hasLocationPermission) {
            Box(modifier = Modifier.fillMaxSize()) {
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(
                        uiState.userLocation ?: LatLng(-12.1221, -77.0298),
                        14f // Reducido de 16f a 14f para ver más área
                    )
                }

                // Sincronizar cámara con ubicación actual CADA VEZ que cambie
                LaunchedEffect(uiState.userLocation) {
                    uiState.userLocation?.let {
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newLatLngZoom(it, 14f), // Reducido a 14f
                            durationMs = 1000
                        )
                    }
                }

                // Mapa ocupa el fondo
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(isMyLocationEnabled = true),
                    uiSettings = MapUiSettings(myLocationButtonEnabled = true)
                ) {
                    uiState.estacionamientosCercanos.forEach { est ->
                        Marker(
                            state = MarkerState(position = LatLng(est.latitud, est.longitud)),
                            title = est.nombre,
                            onClick = {
                                viewModel.selectEstacionamiento(est)
                                false
                            }
                        )
                    }
                }

                // Panel inferior con la lista
                if (uiState.estacionamientosCercanos.isNotEmpty()) {
                    EstacionamientosBottomSheet(
                        estacionamientos = uiState.estacionamientosCercanos,
                        seleccionado = uiState.estacionamientoSeleccionado,
                        onSelect = { viewModel.selectEstacionamiento(it) },
                        onReservaClick = { 
                            navController.navigate(NavRutas.FORM_RESERVA)
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxHeight(0.45f)
                    )
                }
            }
        } else {
            // Pantalla de solicitud permisos
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Necesitamos tu ubicación", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Para mostrarte los estacionamientos más cercanos a ti, requerimos acceso a tu GPS.", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }) {
                    Text("Conceder Permiso")
                }
            }
        }
    }
}

@SuppressLint("MissingPermission")
private fun startLocationUpdates(client: FusedLocationProviderClient, callback: LocationCallback) {
    val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
        .setMinUpdateIntervalMillis(3000).build()
    client.requestLocationUpdates(locationRequest, callback, Looper.getMainLooper())
}
