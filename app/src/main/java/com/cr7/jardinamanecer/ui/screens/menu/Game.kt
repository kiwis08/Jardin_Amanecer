package com.cr7.jardinamanecer.ui.screens.menu

import androidx.room.Entity
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens

//@Entity(tableName = "games", primaryKeys = ["level", "number"])
data class Game(val image: Int, val level: Int, val number: Int, val route: String)
