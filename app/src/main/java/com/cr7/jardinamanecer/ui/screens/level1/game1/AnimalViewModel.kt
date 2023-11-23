package com.cr7.jardinamanecer.ui.screens.level1.game1

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.R
import kotlinx.coroutines.CoroutineScope

class AnimalViewModel(
    private val scope: CoroutineScope): ViewModel() {

    private lateinit var mediaPlayer: MediaPlayer

    val nombresAnimales = listOf("vaca", "caballo", "oveja", "cerdo", "gallo")

    val animales = listOf(
        (R.drawable.vaca),
        (R.drawable.caballo),
        (R.drawable.oveja),
        (R.drawable.cerdo),
        (R.drawable.gallo)
    )


    val audioSource = listOf(
        (R.raw.mugida),
        (R.raw.lavaca)
    )


}