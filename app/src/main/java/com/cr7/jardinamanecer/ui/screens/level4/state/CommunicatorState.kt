package com.cr7.jardinamanecer.ui.screens.level4.state

import com.cr7.jardinamanecer.model.level4.ComunicatorItem

data class CommunicatorState(
    val items: List<ComunicatorItem> = listOf<ComunicatorItem>(),
    val isButtonEnabled: Boolean = true,
    var selectedItems: List<ComunicatorItem> = listOf<ComunicatorItem>(),
)