package com.cr7.jardinamanecer.ui.screens.level4.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.cr7.jardinamanecer.ui.screens.level4.model.ComunicatorItem
 class CommunicatorState {
     var items by mutableStateOf<List<ComunicatorItem>>(listOf())
     var isButtonEnabled by mutableStateOf(true)
     var selectedItems by mutableStateOf<List<ComunicatorItem>>(listOf())
     var categories by mutableStateOf<List<String>>(listOf())
     var selectedCategory by mutableStateOf("")
 }