package com.pratclot.kmm1.android.repo

import com.pratclot.kmm1.backend.Backend.readTemperaturesFromBackend
import com.pratclot.kmm1.data.Temps
import io.ktor.client.HttpClient
import javax.inject.Inject

class RepoAbstraction @Inject constructor(var wsClient: HttpClient) {
    suspend fun readTemperatures(updateTemps: (Temps) -> Unit) {
        readTemperaturesFromBackend(wsClient, updateTemps)
    }
}