package com.pratclot.kmm1.backend

import com.pratclot.kmm1.WS_SERVER_HOST
import com.pratclot.kmm1.WS_SERVER_PATH
import com.pratclot.kmm1.data.Temps
import com.pratclot.kmm1.data.toTemps
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.wss
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText

object Backend : IBackend {
    override suspend fun readTemperaturesFromBackend(
        wsClient: HttpClient,
        invokeCallback: (obj: Temps) -> Unit
    ) {
        wsClient
            .wss(
                host = WS_SERVER_HOST,
                path = WS_SERVER_PATH
            ) {
                for (message in incoming) {
                    message as Frame.Text
                    val readText = message.readText()
                    val obj = readText.toTemps()
                    invokeCallback(obj)
                }
            }
    }
}