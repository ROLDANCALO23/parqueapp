package com.eparking.parqueapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eparking.parqueapp.presentation.viewmodel.mapa.Estacionamiento

/**
 * Componente que muestra la lista de estacionamientos en un panel inferior.
 */
@Composable
fun EstacionamientosBottomSheet(
    estacionamientos: List<Estacionamiento>,
    seleccionado: Estacionamiento?,
    onSelect: (Estacionamiento) -> Unit,
    onReservaClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            // Indicador de arrastre (visual)
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.weight(1f, fill = false),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(estacionamientos) { est ->
                    EstacionamientoItem(
                        estacionamiento = est,
                        isSelected = seleccionado?.id == est.id,
                        onClick = { onSelect(est) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Reserva General (más simple y profesional)
            ParqueaButton(
                text = if (seleccionado != null) "Reservar en ${seleccionado.nombre}" else "Selecciona un lugar",
                onClick = { if (seleccionado != null) onReservaClick() },
                modifier = Modifier.fillMaxWidth(),
                containerColor = if (seleccionado != null) MaterialTheme.colorScheme.primary else Color.LightGray
            )
        }
    }
}
