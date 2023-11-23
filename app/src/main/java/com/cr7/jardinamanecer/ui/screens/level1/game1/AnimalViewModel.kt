package com.cr7.jardinamanecer.ui.screens.level1.game1

import android.graphics.fonts.Font
import android.media.MediaPlayer
import android.os.Bundle
import androidx.compose.ui.text.font.FontFamily
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.ui.screens.level1.DataBaseItem
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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