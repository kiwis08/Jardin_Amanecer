package com.cr7.jardinamanecer.ui.screens.admin

import com.google.firebase.firestore.DocumentId

data class Administrator(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val lastName: String = "",
    val birthdate: String = "",
    val gender: String = "",
    val level1Students: Int = 0,
    val level2Students: Int = 0,
    val level3Students: Int = 0,
    val level4Students: Int = 0,
)
