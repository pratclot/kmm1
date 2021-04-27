package com.pratclot.kmm1.backend

import com.pratclot.kmm1.data.Temps
import io.ktor.client.HttpClient

interface IBackend {
    suspend fun readTemperaturesFromBackend(
        wsClient: HttpClient,
        invokeCallback: (obj: Temps) -> Unit
    )
}