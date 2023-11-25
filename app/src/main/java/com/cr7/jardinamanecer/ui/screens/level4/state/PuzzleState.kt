package com.cr7.jardinamanecer.ui.screens.level4.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.cr7.jardinamanecer.R

class PuzzleState {
    private val images = listOf<Int>(
        R.drawable.row_1_column_1,
        R.drawable.row_1_column_2,
        R.drawable.row_1_column_3,
        R.drawable.row_2_column_1,
        R.drawable.row_2_column_2,
        R.drawable.row_2_column_3,
        R.drawable.row_3_column_1,
        R.drawable.row_3_column_2,
        R.drawable.row_3_column_3,
    )
    private val imagesInRandomOrder = images.shuffled()

    var currentRandomImagesInSelectables by mutableStateOf<MutableMap<Int, Int>>(
        mutableMapOf(
            0 to imagesInRandomOrder[0],
            1 to imagesInRandomOrder[1],
            2 to imagesInRandomOrder[2],
            3 to imagesInRandomOrder[3],
            4 to imagesInRandomOrder[4],
            5 to imagesInRandomOrder[5],
            6 to imagesInRandomOrder[6],
            7 to imagesInRandomOrder[7],
            8 to imagesInRandomOrder[8],
        )
    )



    val imagesCorrectPositions: Map<Int, Int> = mapOf(
        images[0] to 0,
        images[1] to 1,
        images[2] to 2,
        images[3] to 3,
        images[4] to 4,
        images[5] to 5,
        images[6] to 6,
        images[7] to 7,
        images[8] to 8,
    )

    var currentImageInPuzzle  by mutableStateOf<MutableMap<Int, Int>>(
        mutableMapOf(
            0 to R.drawable.white_rectangle,
            1 to R.drawable.white_rectangle,
            2 to R.drawable.white_rectangle,
            3 to R.drawable.white_rectangle,
            4 to R.drawable.white_rectangle,
            5 to R.drawable.white_rectangle,
            6 to R.drawable.white_rectangle,
            7 to R.drawable.white_rectangle,
            8 to R.drawable.white_rectangle,
        )
    )

}