package com.cr7.jardinamanecer.ui.screens.level4.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.ui.screens.level4.model.MemoryItem

class MemoryState {
    val categories: List<String> = listOf(
        "Vestimenta",
        "Animales",
        "Alimentos",
        "Transporte"
    )
    var items: List<MemoryItem> = listOf(
        MemoryItem("Gorro", "Vestimenta", R.drawable.gorro),
        MemoryItem("Chamarra", "Vestimenta", R.drawable.chamarra),
        MemoryItem("Falda", "Vestimenta", R.drawable.falda),
        MemoryItem("Pantalones", "Vestimenta", R.drawable.pantalones),
        MemoryItem("Gorro", "Vestimenta", R.drawable.gorro),
        MemoryItem("Chamarra", "Vestimenta", R.drawable.chamarra),
        MemoryItem("Falda", "Vestimenta", R.drawable.falda),
        MemoryItem("Pantalones", "Vestimenta", R.drawable.pantalones),
    )
    var selectedCategory by mutableStateOf("")
    var selectedItem by mutableStateOf<MemoryItem?>(null)
    var selectedWrongItem by mutableStateOf<MemoryItem?>(null)
    var guessedItems by mutableStateOf<List<MemoryItem>>(listOf())
}