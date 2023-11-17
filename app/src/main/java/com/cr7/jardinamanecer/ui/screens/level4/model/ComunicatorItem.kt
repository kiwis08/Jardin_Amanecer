package com.cr7.jardinamanecer.ui.screens.level4.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ComunicatorItem {
    var title: String = ""
    var category: String = ""
    var imageName: String = ""
    var imageUrl by mutableStateOf("")

    constructor(title: String, category: String, imageName: String) {
        this.title = title
        this.category = category
        this.imageName = imageName
    }
}