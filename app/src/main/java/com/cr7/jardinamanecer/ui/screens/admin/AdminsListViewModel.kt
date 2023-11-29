package com.cr7.jardinamanecer.ui.screens.admin

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow

class AdminsListViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    val adminsList: Flow<List<Administrator>>
        get() = firestore.collection("administrators")
        .dataObjects()
}