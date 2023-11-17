package com.cr7.jardinamanecer.ui.screens.level4.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.ui.screens.level4.model.MemoryItem
import com.cr7.jardinamanecer.ui.screens.level4.state.MemoryState

class MemoryViewModel: ViewModel() {
    private val _state = mutableStateOf(MemoryState())
    val state: State<MemoryState> = _state

    fun onCategorySelected(category: String) {
        _state.value.selectedCategory = category
    }

    fun onItemSelected(item: MemoryItem) {
        if (state.value.selectedItem == null) {
            // Turn the card
            _state.value.selectedItem = item
        } else {
            if (state.value.selectedItem!!.title == item.title) {
                // Keep both cards turned
                _state.value.guessedItems = _state.value.guessedItems + item
                _state.value.selectedItem = null
                _state.value.selectedWrongItem = null
            } else {
                // Turn the card for a while, then turn it back
                _state.value.selectedWrongItem = item
                Thread {
                    Thread.sleep(1000)
                    _state.value.selectedItem = null
                    _state.value.selectedWrongItem = null
                }.start()
            }
        }
    }

    fun resetState() {
        _state.value = MemoryState()
    }

    fun isTurned(item: MemoryItem): Boolean {
        if (_state.value.selectedItem == item) {
            return true
        }
        if (_state.value.selectedWrongItem == item) {
            return true
        }
        if (_state.value.guessedItems.map { it.title }.contains(item.title)) {
            return true
        }
        return false
    }

    init {
        _state.value.items = _state.value.items.shuffled()
    }


}