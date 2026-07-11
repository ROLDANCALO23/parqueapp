package com.eparking.parqueapp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Componente visual para campos que abren un selector (Fecha, Hora, Listas, etc.)
 */
@Composable
fun ParqueaSelectionField(
    label: String,
    value: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label, 
            fontSize = 11.sp, 
            fontWeight = FontWeight.Bold, 
            color = Color.Gray, 
            modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(16.dp))
                .clickable { onClick() },
            color = Color(0xFFF9F9FF),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = value, 
                    fontSize = 15.sp, 
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1D1B20)
                )
                Icon(
                    imageVector = icon, 
                    contentDescription = null, 
                    tint = Color.Gray, 
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
