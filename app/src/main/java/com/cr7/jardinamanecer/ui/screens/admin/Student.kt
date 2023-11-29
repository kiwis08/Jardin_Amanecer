package com.cr7.jardinamanecer.ui.screens.admin

import com.google.firebase.firestore.DocumentId
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Student(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val lastName: String = "",
    val birthdate: String = "",
    val cogLevel: Int = 0,
    val adminId: String = "",
    val gender: String = "",
    val games: Map<String, List<Int>> = emptyMap(),
)