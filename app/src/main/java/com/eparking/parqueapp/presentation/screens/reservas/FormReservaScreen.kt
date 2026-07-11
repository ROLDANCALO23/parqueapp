package com.eparking.parqueapp.presentation.screens.reservas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.eparking.parqueapp.presentation.components.ParqueaButton
import com.eparking.parqueapp.presentation.components.ParqueaSelectionField
import com.eparking.parqueapp.presentation.navigation.NavRutas
import com.eparking.parqueapp.presentation.viewmodel.mapa.MapaViewModel
import com.eparking.parqueapp.presentation.viewmodel.reservas.FormReservaViewModel
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormReservaScreen(
    mapaViewModel: MapaViewModel,
    formViewModel: FormReservaViewModel,
    navController: NavHostController
) {
    val mapaState by mapaViewModel.uiState.collectAsState()
    val formState by formViewModel.uiState.collectAsState()
    val estacionamiento = mapaState.estacionamientoSeleccionado

    // Inicializamos el total al entrar
    LaunchedEffect(estacionamiento) {
        estacionamiento?.let { formViewModel.initReserva(it.precioHora) }
    }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePickerInicio by remember { mutableStateOf(false) }
    var showTimePickerFin by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBFBFF))
            .padding(24.dp)
    ) {
        // 1. Header Estacionamiento
        estacionamiento?.let { est ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 2.dp
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(48.dp), color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp)) {
                        Box(contentAlignment = Alignment.Center) { Text("P", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp) }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = est.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = est.direccion, color = Color.Gray, fontSize = 12.sp)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "S/ ${String.format(Locale.US, "%.2f", est.precioHora)}/hr", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        Text(text = "Tarifa base", color = Color.Gray, fontSize = 10.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Sección Entrada
        SectionTitle(title = "Entrada", icon = Icons.Default.Schedule)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ParqueaSelectionField(
                label = "FECHA",
                value = formState.fecha.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                icon = Icons.Default.CalendarMonth,
                modifier = Modifier.weight(1.5f),
                onClick = { showDatePicker = true }
            )
            ParqueaSelectionField(
                label = "HORA",
                value = formState.horaInicio.format(DateTimeFormatter.ofPattern("hh:mm a")),
                icon = Icons.Default.Schedule,
                modifier = Modifier.weight(1f),
                onClick = { showTimePickerInicio = true }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 3. Sección Salida
        SectionTitle(title = "Salida", icon = Icons.Default.Schedule)
        ParqueaSelectionField(
            label = "HORA",
            value = formState.horaFin.format(DateTimeFormatter.ofPattern("hh:mm a")),
            icon = Icons.Default.Schedule,
            onClick = { showTimePickerFin = true }
        )

        Spacer(modifier = Modifier.weight(1f))

        // 4. Duración Total y Cálculo de Pago
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(modifier = Modifier.fillMaxWidth(), color = Color(0xFFF6F2FF), shape = RoundedCornerShape(12.dp)) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Icon(Icons.Default.Schedule, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "DURACIÓN TOTAL: ${formState.duracionHoras} HORAS", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
            
            // Texto del Total a Pagar
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Total estimado:", color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "S/ ${String.format(Locale.US, "%.2f", formState.totalPagar)}",
                    color = Color(0xFF1D1B20),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ParqueaButton(
            text = "Reservar ahora",
            onClick = { 
                estacionamiento?.let { est ->
                    formViewModel.confirmarReserva(est.id) 
                    navController.navigate(NavRutas.MAPA) {
                        popUpTo(NavRutas.MAPA) { inclusive = true }
                    }
                } 
            }
        )
    }

    // --- DIÁLOGOS ---
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val date = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                        formViewModel.onFechaChange(date)
                    }
                    showDatePicker = false
                }) { Text("Aceptar") }
            },
            dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Cancelar") } }
        ) { DatePicker(state = datePickerState) }
    }

    if (showTimePickerInicio) {
        val timePickerState = rememberTimePickerState(initialHour = formState.horaInicio.hour, initialMinute = formState.horaInicio.minute)
        AlertDialog(
            onDismissRequest = { showTimePickerInicio = false },
            confirmButton = {
                TextButton(onClick = {
                    val nuevaHora = LocalTime.of(timePickerState.hour, timePickerState.minute)
                    estacionamiento?.let { formViewModel.onHoraInicioChange(nuevaHora, it.precioHora) }
                    showTimePickerInicio = false
                }) { Text("Aceptar") }
            },
            dismissButton = { TextButton(onClick = { showTimePickerInicio = false }) { Text("Cancelar") } },
            text = { TimePicker(state = timePickerState) }
        )
    }

    if (showTimePickerFin) {
        val timePickerState = rememberTimePickerState(initialHour = formState.horaFin.hour, initialMinute = formState.horaFin.minute)
        AlertDialog(
            onDismissRequest = { showTimePickerFin = false },
            confirmButton = {
                TextButton(onClick = {
                    val nuevaHora = LocalTime.of(timePickerState.hour, timePickerState.minute)
                    estacionamiento?.let { formViewModel.onHoraFinChange(nuevaHora, it.precioHora) }
                    showTimePickerFin = false
                }) { Text("Aceptar") }
            },
            dismissButton = { TextButton(onClick = { showTimePickerFin = false }) { Text("Cancelar") } },
            text = { TimePicker(state = timePickerState) }
        )
    }
}

@Composable
fun SectionTitle(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 12.dp)) {
        Icon(icon, null, tint = Color(0xFF4A148C), modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, color = Color(0xFF4A148C), fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}
