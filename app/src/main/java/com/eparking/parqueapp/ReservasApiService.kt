package com.eparking.parqueapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ReservasApiService {

    @GET("dev/reservas")
    suspend fun obtenerReservas(): List<ReservaDto>
}

object RetrofitClient {

    private const val BASE_URL =
        "https://zu4zhp0cb3.execute-api.us-east-1.amazonaws.com/"

    val reservasApiService: ReservasApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReservasApiService::class.java)
    }
}
