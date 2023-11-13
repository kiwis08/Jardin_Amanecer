package com.cr7.jardinamanecer.ui.screens.Level1.game2

import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PartesViewModel(
private val scope: CoroutineScope
) : ViewModel()  {

    private val folderPath = "Karla/PartesCuerpo/Imagenes"

    private val _partesItemList = MutableStateFlow<List<PartesItem>>(emptyList())
    val partesItemList: StateFlow<List<PartesItem>> get() = _partesItemList
    init {
        scope.launch {
            val items = getImageUrlsAndTitles(folderPath)
            _partesItemList.value = items
            println("Parteslist: $items")

        }
    }

    private suspend fun getImageUrlsAndTitles(folderPath: String): List<PartesItem> {
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.reference.child(folderPath)

        return try {
            val items = storageReference.listAll().await()

            // Map the items to AnimalsItem instances
            items.items.map {
                PartesItem(
                    name = it.name,
                    imageUrl = it.downloadUrl.await().toString()
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}