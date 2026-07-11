package com.eparking.parqueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.eparking.parqueapp.presentation.navigation.AppNavigation
import com.eparking.parqueapp.presentation.components.AppScaffold
import com.eparking.parqueapp.ui.theme.ParqueAppTheme
import com.eparking.parqueapp.di.AppContainer

class MainActivity : ComponentActivity() {

    private val container by lazy {
        (application as ParqueoApplication).container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParqueAppTheme {
                val navHostController = rememberNavController()
                AppScaffold(navHostController) { paddingValues ->
                    AppNavigation(navHostController, container, paddingValues)
                }
            }
        }
    }
}
