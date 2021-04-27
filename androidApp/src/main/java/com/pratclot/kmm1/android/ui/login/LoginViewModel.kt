package com.pratclot.kmm1.android.ui.login

import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.pratclot.kmm1.SafeStorage
import com.pratclot.kmm1.android.di.JwtInterceptor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var jwtInterceptor: JwtInterceptor) : ViewModel() {

    lateinit var googleSignInClient: GoogleSignInClient
    var jwtToken: String = ""
        set(value) {
            saveJwtToken(value)
            updateJwtInterceptor(value)

            field = value
        }

    init {
        SafeStorage.getJwtToken()?.let {
            jwtToken = it
        }
    }

    @Inject
    lateinit var auth: FirebaseAuth

    private fun updateJwtInterceptor(value: String) {
        jwtInterceptor.token = value
    }

    private fun saveJwtToken(token: String) {
        SafeStorage.saveJwtToken(token)
    }
}
