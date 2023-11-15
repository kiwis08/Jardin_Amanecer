package com.cr7.jardinamanecer.ui.screens.level1.game1

import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.ui.screens.level1.DataBaseItem
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AnimalViewModel(
    private val scope: CoroutineScope): ViewModel() {
    private val folderPath = "Karla/Animales/Imagenes"

    private val _animalsItemList = MutableStateFlow<List<DataBaseItem>>(emptyList())
    val animalsItemList: StateFlow<List<DataBaseItem>> get() = _animalsItemList
    init {
        scope.launch {
            val items = getImageUrlsAndTitles(folderPath)
            _animalsItemList.value = items
            println("AnimalsItemList: $items")

        }
    }

    private suspend fun getImageUrlsAndTitles(folderPath: String): List<DataBaseItem> {
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.reference.child(folderPath)

        return try {
            val items = storageReference.listAll().await()

            // Map the items to AnimalsItem instances
            items.items.map {
                DataBaseItem(
                    title = it.name,
                    contentUrl = it.downloadUrl.await().toString()
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private val folderPath_audio = "Karla/Animales/Audios"

    private val _animalsAudioList = MutableStateFlow<List<DataBaseItem>>(emptyList())
    val animalsAudioList: StateFlow<List<DataBaseItem>> get() = _animalsAudioList
    init {
        scope.launch {
            val audio_items = getImageUrlsAndTitles(folderPath)
            _animalsAudioList.value = audio_items
            println("AnimalsAudioList: $audio_items")

        }
    }

    private suspend fun getAudioUrlsAndTitles(folderPath_audio: String): List<DataBaseItem> {
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.reference.child(folderPath_audio)

        return try {
            val items = storageReference.listAll().await()

            // Map the items to AnimalsItem instances
            items.items.map {
                DataBaseItem(
                    title = it.name,
                    contentUrl = it.downloadUrl.await().toString()
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }




}