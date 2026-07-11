package com.eparking.parqueapp.presentation.screens.inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.eparking.parqueapp.presentation.navigation.NavRutas
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eparking.parqueapp.R
import com.eparking.parqueapp.presentation.components.FeatureItem
import com.eparking.parqueapp.presentation.components.ParqueaButton

@Composable
fun InicioScreen(navController: NavHostController) {
    // Usamos el color Scheme del tema centralizado
    val colorScheme = MaterialTheme.colorScheme

    Box(modifier = Modifier.fillMaxSize()) {
        // --- 1. Imagen de Fondo ---
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Placeholder
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // --- 2. Gradiente Oscuro ---
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.4f), Color.Black.copy(alpha = 0.7f)),
                        startY = 0f,
                        endY = 1000f
                    )
                )
        )

        // --- 3. Contenido Principal ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ParqueApp",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = "Idioma",
                        tint = Color.LightGray
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.2f))

            // Badge y Título
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Surface(
                    color = colorScheme.primary.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SOLUCIÓN DE ESTACIONAMIENTO INTELIGENTE",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Busca y reserva\nestacionamiento\nen segundos",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 40.sp
                )
            }

            Spacer(modifier = Modifier.weight(0.3f))

            // --- 4. Tarjeta Blanca Inferior ---
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colorScheme.surface,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .navigationBarsPadding()
                ) {
                    FeatureItem(
                        icon = Icons.Default.QrCodeScanner,
                        title = "Experiencia totalmente digital",
                        description = "Olvídate de la máquina de boletos. La entrada y el pago son completamente sin contacto.",
                        iconBg = colorScheme.primary,
                        iconTint = colorScheme.surfaceVariant
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    FeatureItem(
                        icon = Icons.Default.ElectricCar,
                        title = "Soporte para Flotas Premium",
                        description = "Accede a lugares reservados con carga para vehículos eléctricos en toda la ciudad.",
                        iconBg = colorScheme.primary,
                        iconTint = colorScheme.surfaceVariant
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    ParqueaButton(
                        text = "Comenzar",
                        onClick = { navController.navigate(NavRutas.REGISTRO) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ParqueaButton(
                        text = "Iniciar Sesión",
                        isOutlined = true,
                        onClick = { navController.navigate(NavRutas.LOGIN) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Al continuar, aceptas nuestros Términos de Servicio",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
