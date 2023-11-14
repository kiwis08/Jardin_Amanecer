package com.cr7.jardinamanecer.ui.screens.Level1.game1

import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AnimalViewModel(
    private val scope: CoroutineScope): ViewModel() {
    private val folderPath = "Karla/Animales/Imagenes"

    private val _animalsItemList = MutableStateFlow<List<AnimalsItem>>(emptyList())
    val animalsItemList: StateFlow<List<AnimalsItem>> get() = _animalsItemList
    init {
        scope.launch {
            val items = getImageUrlsAndTitles(folderPath)
            _animalsItemList.value = items
            println("AnimalsItemList: $items")

        }
    }

    private suspend fun getImageUrlsAndTitles(folderPath: String): List<AnimalsItem> {
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.reference.child(folderPath)

        return try {
            val items = storageReference.listAll().await()

            // Map the items to AnimalsItem instances
            items.items.map {
                AnimalsItem(
                    title = it.name,
                    imageUrl = it.downloadUrl.await().toString()
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }


}