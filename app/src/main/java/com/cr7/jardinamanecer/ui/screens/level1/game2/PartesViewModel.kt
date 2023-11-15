package com.cr7.jardinamanecer.ui.screens.level1.game2

import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.ui.screens.level1.DataBaseItem
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PartesViewModel(
scope: CoroutineScope
) : ViewModel()  {

    private val folderPath = "Karla/PartesCuerpo/Imagenes/Tarjetas"

    private val _partesItemList = MutableStateFlow<List<DataBaseItem>>(emptyList())
    val partesItemList: StateFlow<List<DataBaseItem>> get() = _partesItemList
    init {
        scope.launch {
            val items = getImageUrlsAndTitles(folderPath)
            _partesItemList.value = items
            println("Parteslist: $items")

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
}