package com.cr7.jardinamanecer.ui.screens.admin


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.Flow

class AdminStudentListViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    val studentList: Flow<List<Student>> get() = firestore.collection("users")
        .whereEqualTo("adminID", auth.currentUser?.uid)
        .dataObjects()


}