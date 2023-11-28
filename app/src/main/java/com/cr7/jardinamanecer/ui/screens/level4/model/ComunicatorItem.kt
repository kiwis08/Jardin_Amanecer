package com.cr7.jardinamanecer.ui.screens.level4.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.Entity

@Entity(tableName = "communicator_item", primaryKeys = ["title"])
class ComunicatorItem {
    var title: String = ""
    var category: String = ""
    var imageName: String = ""
    var imageUrl by mutableStateOf("")
    var imageLocalPath: String = ""

    constructor(title: String, category: String, imageName: String) {
        this.title = title
        this.category = category
        this.imageName = imageName
    }
}