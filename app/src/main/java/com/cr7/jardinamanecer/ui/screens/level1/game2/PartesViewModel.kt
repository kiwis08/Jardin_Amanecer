package com.cr7.jardinamanecer.ui.screens.level1.game2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.R
import kotlinx.coroutines.CoroutineScope

class PartesViewModel(
scope: CoroutineScope
) : ViewModel()  {

    var index_glo by mutableStateOf(0)

    // Other ViewModel methods and properties

    fun setIndex(index: Int) {
        index_glo = index
    }


    val imagenes = listOf(
        (R.drawable.hombroyrodilla),
        (R.drawable.narizyoreja),
        (R.drawable.ojoylabios),
        (R.drawable.piesymanos)
    )

    val imagenes_ind = listOf(

        (R.drawable.ojo),
        (R.drawable.manos),
        (R.drawable.pies),
        (R.drawable.labios),
        (R.drawable.nariz),
        (R.drawable.oreja),
        (R.drawable.hombro),
        (R.drawable.rodilla)
        )

    fun getImagesForIndex(index: Int): List<Int> {
        return when (index) {
            0 -> listOf(
                (R.drawable.hombro),
                (R.drawable.rodilla))

            1 -> listOf(
                (R.drawable.nariz),
                (R.drawable.oreja))

            2 -> listOf(
                (R.drawable.ojo),
                (R.drawable.labios))

            3 -> listOf(
                (R.drawable.pies),
                (R.drawable.manos))

            else -> emptyList()
        }
    }



}