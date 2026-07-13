package com.eparking.parqueapp.di

import com.eparking.parqueapp.core.network.RetrofitClient

/**
 * Módulo para proveer la infraestructura de red.
 */
class NetworkModule {
    val apiService by lazy { RetrofitClient.apiService }
}
