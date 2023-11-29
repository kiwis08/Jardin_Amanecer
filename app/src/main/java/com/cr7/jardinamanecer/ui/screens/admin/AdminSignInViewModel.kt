package com.cr7.jardinamanecer.ui.screens.admin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.Flow

class AdminSignInViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

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

    suspend fun getAdmin(): Administrator? {
        try {
            val adminId = getAdminId()
            val snapshot = firestore.collection("administrators")
                .document(adminId)
                .get(Source.SERVER)
                .await()
            val data = snapshot.data ?: return null
            return Administrator(
                id = snapshot.id,
            name = data["name"] as String,
            lastName = data["lastName"] as String,
            birthdate = data["birthdate"] as String,
            gender = data["gender"] as String,
            level1Students = (data["level1Students"] as Long).toInt(),
            level2Students = (data["level2Students"] as Long).toInt(),
            level3Students = (data["level3Students"] as Long).toInt(),
            level4Students = (data["level4Students"] as Long).toInt(),
            )
        } catch (e: Exception) {
            println(e)
            return null
        }
        return null
    }
}