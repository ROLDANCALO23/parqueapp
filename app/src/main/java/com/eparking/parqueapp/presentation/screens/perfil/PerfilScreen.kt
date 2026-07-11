package com.eparking.parqueapp.presentation.screens.perfil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eparking.parqueapp.presentation.components.ParqueaButton
import com.eparking.parqueapp.presentation.components.ParqueaTextField
import com.eparking.parqueapp.presentation.components.ProfileHeader
import com.eparking.parqueapp.presentation.viewmodel.perfil.PerfilViewModel

@Composable
fun PerfilScreen(viewModel: PerfilViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp)
    ) {
        // 1. Barra Superior interna
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Perfil",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    imageVector = Icons.Default.Settings, 
                    contentDescription = "Configuración", 
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        // 2. Encabezado de Perfil (Componente Reutilizable)
        ProfileHeader(
            nombre = uiState.nombre,
            email = uiState.email,
            onEditClick = { /* Lógica para cambiar foto */ }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 3. Formulario de Datos
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, Color(0xFFF0F0F0))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Nombre
                LabelText("NOMBRE COMPLETO")
                ParqueaTextField(
                    value = uiState.nombre,
                    onValueChange = { viewModel.onNombreChange(it) },
                    placeholder = "Nombre Completo"
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Celular
                LabelText("CELULAR")
                ParqueaTextField(
                    value = uiState.telefono,
                    onValueChange = { viewModel.onTelefonoChange(it) },
                    placeholder = "Celular"
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Correo Electrónico (En reemplazo de Contraseña)
                LabelText("CORREO ELECTRÓNICO")
                ParqueaTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    placeholder = "correo@ejemplo.com"
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 4. Botón Guardar
        ParqueaButton(
            text = "Guardar Cambios",
            onClick = { viewModel.onGuardarCambios() }
        )
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun LabelText(text: String) {
    Text(
        text = text,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF6B4FA9),
        modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
    )
}
