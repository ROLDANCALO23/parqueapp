package com.eparking.parqueapp.presentation.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eparking.parqueapp.R
import com.eparking.parqueapp.presentation.components.AuthClickableText
import com.eparking.parqueapp.presentation.components.AuthHeader
import com.eparking.parqueapp.presentation.components.ParqueaButton
import com.eparking.parqueapp.presentation.components.ParqueaTextField
import com.eparking.parqueapp.presentation.navigation.NavRutas
import com.eparking.parqueapp.presentation.viewmodel.perfil.RegistrationViewModel

@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Encabezado con imagen
        AuthHeader(
            image = painterResource(id = R.drawable.ic_launcher_background), // Reemplazar con la imagen real del parking
            title = "Datos Personales",
            subtitle = "Comencemos con tu información básica para crear tu perfil.",
            isLogo = false
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Formulario de Registro
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Nombre Completo
            ParqueaTextField(
                value = uiState.nombre,
                onValueChange = { viewModel.onNombreChange(it) },
                placeholder = "Nombre Completo",
                leadingIcon = Icons.Default.Person
            )

            // Correo Electrónico
            ParqueaTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = "Correo Electrónico",
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )

            // Celular con prefijo +51
            ParqueaTextField(
                value = uiState.telefono,
                onValueChange = { viewModel.onTelefonoChange(it) },
                placeholder = "Celular",
                leadingIcon = Icons.Default.Phone,
                prefix = "+51",
                keyboardType = KeyboardType.Phone
            )

            // Contraseña
            ParqueaTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = "Contraseña",
                leadingIcon = Icons.Default.Lock,
                isPassword = true
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 3. Botón de Registro
        ParqueaButton(
            text = "Completar Registro",
            onClick = { 
                viewModel.onRegistroClick() 
                navController.navigate(NavRutas.MAPA) {
                    popUpTo(NavRutas.REGISTRO) { inclusive = true }
                }
            },
            trailingIcon = Icons.Default.CheckCircle
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 4. Enlace a Login
        AuthClickableText(
            normalText = "¿Ya tienes una cuenta?",
            clickableText = "Iniciar Sesión",
            onClick = { navController.navigate(NavRutas.LOGIN) }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}
