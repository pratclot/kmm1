package com.pratclot.kmm1.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OkHttpModule {
    @Singleton
    @Provides
    fun provideJwtInterceptor() = JwtInterceptor()
}

class JwtInterceptor : Interceptor {
    var token = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}