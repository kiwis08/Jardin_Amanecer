package com.cr7.jardinamanecer.ui.screens.level4.model

import androidx.room.Entity

@Entity(tableName = "category", primaryKeys = ["title"])
data class CommunicatorCategory(val title: String)
