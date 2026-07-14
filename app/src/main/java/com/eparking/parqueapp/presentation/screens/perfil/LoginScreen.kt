package com.eparking.parqueapp.presentation.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.eparking.parqueapp.R
import com.eparking.parqueapp.presentation.components.AuthClickableText
import com.eparking.parqueapp.presentation.components.AuthHeader
import com.eparking.parqueapp.presentation.components.ParqueaButton
import com.eparking.parqueapp.presentation.components.ParqueaTextField
import com.eparking.parqueapp.presentation.navigation.NavRutas
import com.eparking.parqueapp.presentation.viewmodel.perfil.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(uiState.loginExitoso) {
        if (uiState.loginExitoso) {
            navController.navigate(NavRutas.MAPA)
            viewModel.onNavegacionConsumida()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // 1. Encabezado con Logo
        AuthHeader(
            image = painterResource(id = R.drawable.ic_launcher_foreground),
            title = "ParqueApp",
            subtitle = "Tu solución premium de estacionamiento urbano.",
            isLogo = true
        )

        Spacer(modifier = Modifier.height(40.dp))

        // 2. Formulario
        Column(modifier = Modifier.fillMaxWidth()) {
            // Email
            Text(
                text = "CORREO ELECTRÓNICO",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ParqueaTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = "nombre@ejemplo.com",
                leadingIcon = Icons.Default.AlternateEmail
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Password
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CONTRASEÑA",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                TextButton(onClick = { /* TODO */ }) {
                    Text(
                        text = "¿OLVIDASTE?",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
            ParqueaTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = "••••••••",
                leadingIcon = Icons.Default.Lock,
                isPassword = true
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Badge de Seguridad
        Row(
            modifier = Modifier.align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Security,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Cifrado seguro de extremo a extremo activo",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 4. Botón de Ingreso
        ParqueaButton(
            text = "Iniciar Sesión",
            onClick = { viewModel.onLoginClick() },
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward
        )

        Spacer(modifier = Modifier.weight(1f))

        // 5. Enlace a Registro
        AuthClickableText(
            normalText = "¿No tienes una cuenta?",
            clickableText = "Crear Cuenta",
            onClick = { navController.navigate(NavRutas.REGISTRO) }
        )
    }
}
