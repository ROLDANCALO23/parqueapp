package com.eparking.parqueapp

import android.app.Application
import com.eparking.parqueapp.di.AppContainer

/**
 * Clase Application personalizada para la aplicación.
 * Aquí se inicializa el AppContainer que vivirá durante todo el ciclo de vida de la app.
 */
class ParqueoApplication : Application() {
    
    // Contenedor de dependencias manual
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}
