package com.eparking.parqueapp.data.remote.datasource

import android.util.Log
import com.eparking.parqueapp.core.network.ApiService
import com.eparking.parqueapp.core.util.Constantes
import com.eparking.parqueapp.data.remote.dto.InsertarReservaRequest
import com.eparking.parqueapp.data.remote.dto.LoginRequest

/**
 * Fuente de datos remota. Maneja las peticiones de red.
 */
class RemoteDataSource(private val apiService: ApiService) {

    suspend fun login(email: String, password: String) =
        apiService.login(Constantes.LOGIN_URL, LoginRequest(email, password))

    suspend fun getReservas() = try {
        Log.d("RemoteDataSource", "Llamando a apiService.obtenerReservas()...")
        val response = apiService.obtenerReservas()
        Log.d("RemoteDataSource", "Respuesta recibida: ${response.size} elementos")
        response
    } catch (e: Exception) {
        Log.e("RemoteDataSource", "Error en la llamada de red: ${e.message}")
        throw e
    }
    
    suspend fun insertarReserva(req: InsertarReservaRequest) = apiService.insertarReserva(req)
    
    suspend fun getReservaById(id: String) = apiService.getReservaById(id)
    
    suspend fun actualizarReserva(id: String, req: InsertarReservaRequest) = apiService.actualizarReserva(id, req)
    
    suspend fun eliminarReserva(id: String) = apiService.eliminarReserva(id)
}
