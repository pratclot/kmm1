package com.pratclot.kmm1.storage

import com.netguru.kissme.Kissme

actual object SafeStorage {
    private const val TOKEN = "jwtToken"
    private val storage by lazy { Kissme() }

    actual fun saveJwtToken(token: String) {
        storage.putString(TOKEN, token)
    }

    actual fun getJwtToken(): String? {
        return storage.getString(TOKEN, null)
    }
}
