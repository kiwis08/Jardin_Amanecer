package com.cr7.jardinamanecer.ui.screens.level4.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.ui.screens.level4.state.PuzzleState

class PuzzleViewModel: ViewModel() {
    private val _state = mutableStateOf(PuzzleState())
    val state: State<PuzzleState> = _state



    fun resetState() {
        _state.value = PuzzleState()
    }


}