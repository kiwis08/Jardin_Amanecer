package com.cr7.jardinamanecer.ui.screens.level4.viewmodel

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.ui.screens.level4.model.ComunicatorItem
import com.cr7.jardinamanecer.ui.screens.level4.state.CommunicatorState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.Locale


class CommunicatorViewModel: ViewModel() {
    private val userID = "OMHDUjTUnMiFQOSQGdq3"
    private val database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()


    private val _state = mutableStateOf(CommunicatorState(),)
    val state: State<CommunicatorState> = _state
    private var textToSpeech: TextToSpeech? = null

    fun textToSpeech(context: Context){
        _state.value.isButtonEnabled = false
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
            _state.value.isButtonEnabled = true
        }
    }

    fun onItemSelected(item: ComunicatorItem) {
        _state.value.selectedItems = state.value.selectedItems + item
    }

    fun onItemRemoved(item: ComunicatorItem) {
        _state.value.selectedItems = state.value.selectedItems - item
    }

    fun onCategorySelected(category: String) {
        _state.value.selectedCategory = category
    }

    fun _getItems(onCompletion: () -> Unit = {}) {
        database.collection("level4").document(userID).get()
            .addOnSuccessListener { result ->
                val data = result.data ?: return@addOnSuccessListener
                val communicatorItems = data["communicatorItems"] as List<Map<String, String>>
                val items = communicatorItems.map { item ->
                    ComunicatorItem(
                        title = item["title"]!!,
                        category = item["category"]!!,
                        imageName = item["imageName"]!!,
                    )
                }
                _state.value.items = items
                onCompletion()
            }
    }

    fun _getItemsImagesUrl() {
        val items = state.value.items
        items.forEach { item ->
            storage.reference.child("$userID/${item.category}/${item.imageName}").downloadUrl.addOnSuccessListener { uri ->
                val url = uri.toString()
                _state.value.items.find {
                    it.title == item.title
                }.let {
                    it?.imageUrl = url
                }
            }
        }
    }

    fun _getCategories() {
        database.collection("level4").document("general").get()
            .addOnSuccessListener { result ->
                val data = result.data ?: return@addOnSuccessListener
                val categories = data["communicator_categories"] as List<String>
                _state.value.categories = categories.map { it.lowercase() }
            }
    }

    init {
        _getCategories()
        _getItems { _getItemsImagesUrl() }
    }
}