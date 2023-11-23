package com.cr7.jardinamanecer.ui.screens.level1.game2

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.R
import kotlinx.coroutines.CoroutineScope

class PartesViewModel() : ViewModel()  {

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

    fun getNamesForIndex(index: Int): List<String> {
        return when (index) {
            0 -> listOf(
                "hombro",
                "rodilla")

            1 -> listOf(
                "nariz",
                "oreja")

            2 -> listOf(
                "ojo",
                "labios")

            3 -> listOf(
                "pies",
                "manos")

            else -> emptyList()
        }
    }



    private var textToSpeech: TextToSpeech? = null

    fun textToSpeech(context: Context,indexgen:Int, index: Int){
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = java.util.Locale("es", "MX")
                    txtToSpeech.setSpeechRate(0.3f)

                    val Imagenes = getNamesForIndex(indexgen)
                    val text = Imagenes[index]

                    txtToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
        }
    }


}