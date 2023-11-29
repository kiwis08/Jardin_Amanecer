package com.cr7.jardinamanecer.ui.screens.admin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AdminSignInViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    var state = mutableStateOf(SignInState())
        private set

    fun onEmailChange(email: String) {
        state.value = state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state.value = state.value.copy(password = password)
    }

    suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    fun getAdminId(): String {
        return auth.currentUser?.uid ?: ""
    }
}