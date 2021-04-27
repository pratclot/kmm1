package com.pratclot.kmm1.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.websocket.WebSockets
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {
    @Singleton
    @Provides
    fun provideKtorClient(jwtInterceptor: JwtInterceptor): HttpClient {
        return HttpClient(OkHttp) {
            install(WebSockets)
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            engine {
                addInterceptor(jwtInterceptor)
            }
        }
    }
}
