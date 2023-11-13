package com.cr7.jardinamanecer.ui.screens.level4.viewmodel

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.model.level4.ComunicatorItem
import com.cr7.jardinamanecer.ui.screens.level4.state.CommunicatorState
import java.util.Locale


class CommunicatorViewModel: ViewModel() {
    private val _state = mutableStateOf(CommunicatorState(
        items = listOf(
            ComunicatorItem("Déjame solo", "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-cr7.appspot.com/o/dejamesolo.jpg?alt=media&token=029a5c81-7671-404c-938f-c0cee1145f7f"),
            ComunicatorItem("Dirección", "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-cr7.appspot.com/o/direccion.jpg?alt=media&token=a3582dda-9708-40c8-b3e8-ad0e64b4e41a"),
            ComunicatorItem("Nadie con quien jugar", "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-cr7.appspot.com/o/nadieconquienjugar.jpg?alt=media&token=210bdb84-c4c2-44c8-a5b8-cf0c50879a15"),
            ComunicatorItem("Oh no!", "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-cr7.appspot.com/o/ohno.jpg?alt=media&token=9c259c15-6add-4216-97c5-7eb75df85072"),
            ComunicatorItem("Quiero mostrarte algo", "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-cr7.appspot.com/o/quieromostrartealgo.jpg?alt=media&token=d576cc76-7f3e-4967-a327-1ba622969992"),
            ComunicatorItem("Susurrar", "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-cr7.appspot.com/o/susurrar.jpg?alt=media&token=cb0d83a2-ec5a-4b06-8ea0-64d5181bd860"),
            ComunicatorItem("Te voy a alcanzar", "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-cr7.appspot.com/o/tevoyaalcanzar.jpg?alt=media&token=53b62620-be70-4e9f-a1dd-50e2ee9bf1c9"),
            ComunicatorItem("Yo quiero", "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-cr7.appspot.com/o/yoquiero.jpg?alt=media&token=2cfa2f77-0d61-4ac5-86bf-048c18a5ab92"),
        )
    )
    )
    val state: State<CommunicatorState> = _state
    private var textToSpeech: TextToSpeech? = null

    fun textToSpeech(context: Context){
        _state.value = state.value.copy(
            isButtonEnabled = false
        )
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale("es", "MX")
                    txtToSpeech.setSpeechRate(0.3f)

                    val text = state.value.selectedItems.joinToString(separator = " ") { comunicatorItem ->
                        comunicatorItem.title
                    }

                    txtToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
            _state.value = state.value.copy(
                isButtonEnabled = true
            )
        }
    }

    fun onItemSelected(item: ComunicatorItem) {
        _state.value = state.value.copy(
            selectedItems = state.value.selectedItems + item
        )
    }

    fun onItemRemoved(item: ComunicatorItem) {
        _state.value = state.value.copy(
            selectedItems = state.value.selectedItems - item
        )
    }
}