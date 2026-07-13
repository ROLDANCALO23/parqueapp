package com.eparking.parqueapp.di

import com.eparking.parqueapp.core.network.ApiService
import com.eparking.parqueapp.data.remote.datasource.RemoteDataSource
import com.eparking.parqueapp.data.repository.ReservaRepositoryImpl
import com.eparking.parqueapp.domain.repository.ReservaRepository

/**
 * Módulo para proveer las implementaciones de los repositorios.
 */
class RepositoryModule(private val apiService: ApiService) {
    
    private val remoteDataSource by lazy {
        RemoteDataSource(apiService)
    }

    val reservaRepository: ReservaRepository by lazy {
        ReservaRepositoryImpl(remoteDataSource)
    }
}
