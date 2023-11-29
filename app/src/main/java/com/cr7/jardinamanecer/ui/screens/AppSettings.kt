package com.cr7.jardinamanecer.ui.screens

import com.cr7.jardinamanecer.ui.screens.admin.Student
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings (
    var currentStudent: Student? = null
    )