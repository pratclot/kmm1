package com.pratclot.kmm1

import com.netguru.kissme.Kissme

object SafeStorage {
    private const val TOKEN = "jwtToken"
    private val storage by lazy { Kissme() }

    fun saveJwtToken(token: String) {
        storage.putString(TOKEN, token)
    }

    fun getJwtToken(): String? {
        return storage.getString(TOKEN, null)
    }
}
