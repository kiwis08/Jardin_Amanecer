package com.cr7.jardinamanecer.ui.screens.admin

import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
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