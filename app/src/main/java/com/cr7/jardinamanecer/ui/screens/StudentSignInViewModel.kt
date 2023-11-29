package com.cr7.jardinamanecer.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cr7.jardinamanecer.ui.screens.admin.Student
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.Flow

class StudentSignInViewModel: ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    val studentList: kotlinx.coroutines.flow.Flow<List<Student>>
        get() = firestore.collection("users")
        .dataObjects()

}