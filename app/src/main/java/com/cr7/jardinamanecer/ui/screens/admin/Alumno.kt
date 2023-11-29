package com.cr7.jardinamanecer.ui.screens.admin

import kotlinx.serialization.Serializable

@Serializable
data class Alumno(
    val id: String,
    val nombre: String,
    val edad: Int,
    val Nivel1: Boolean,
    val Nivel2: Boolean,
    val Nivel3: Boolean,
    val Nivel4: Boolean,
    val imagen: Int
)