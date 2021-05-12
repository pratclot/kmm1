package com.pratclot.kmm1.storage

expect object SafeStorage {
    fun saveJwtToken(token: String)
    fun getJwtToken(): String?
}