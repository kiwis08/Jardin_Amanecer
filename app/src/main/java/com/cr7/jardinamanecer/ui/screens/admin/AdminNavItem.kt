package com.cr7.jardinamanecer.ui.screens.admin

import android.icu.text.CaseMap.Title
import androidx.compose.ui.graphics.vector.ImageVector

data class AdminNavItem(
    val title: String,
    val description: String,
    val itemId: String,
    val icon: ImageVector
) {
}
