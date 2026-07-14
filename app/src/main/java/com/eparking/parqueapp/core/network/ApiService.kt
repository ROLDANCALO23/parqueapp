package com.eparking.parqueapp.core.network

import com.eparking.parqueapp.data.remote.dto.ApiResponse
import com.eparking.parqueapp.data.remote.dto.InsertarReservaRequest
import com.eparking.parqueapp.data.remote.dto.LoginRequest
import com.eparking.parqueapp.data.remote.dto.LoginResponse
import com.eparking.parqueapp.data.remote.dto.ReservaDetalleResponse
import com.eparking.parqueapp.data.remote.dto.ReservaDto
import retrofit2.Response
import retrofit2.http.*

/**
 * Definición de Endpoints de la API.
 */
interface ApiService {

    // Login vive en un API Gateway distinto (apiUsuarios), por eso usa @Url con la URL completa.
    @POST
    suspend fun login(@Url url: String, @Body request: LoginRequest): Response<ApiResponse<LoginResponse>>

    @GET("dev/reservas")
    suspend fun obtenerReservas(): List<ReservaDto>

    @POST("dev/reservas")
    suspend fun insertarReserva(@Body request: InsertarReservaRequest): Response<Unit>

    @GET("dev/reservas/{id}")
    suspend fun getReservaById(@Path("id") id: String): Response<ReservaDetalleResponse>

    @PUT("dev/reservas/{id}")
    suspend fun actualizarReserva(@Path("id") id: String, @Body request: InsertarReservaRequest): Response<Unit>

    @DELETE("dev/reservas/{id}")
    suspend fun eliminarReserva(@Path("id") id: String): Response<Unit>
}
