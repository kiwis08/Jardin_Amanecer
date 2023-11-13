package com.cr7.jardinamanecer.ui.screens.Level1.game1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalFoundationApi::class)
class AnimalsViewModel(
    private val state: PagerState,
    private val scope: CoroutineScope
) : ViewModel() {

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

    @OptIn(ExperimentalFoundationApi::class)
    fun onStateAndScopeReady(state: PagerState, scope: CoroutineScope) {
        this.state
        this.scope
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun onPreviousButtonClick() {
        goToPreviousPage()
    }

    fun onNextButtonClick() {
        goToNextPage()
    }

    private fun goToPreviousPage() {
        scope.launch {
            state.scrollToPage(state.currentPage - 1)
        }
    }

    private fun goToNextPage() {
        scope.launch {
            state.scrollToPage(state.currentPage + 1)
        }
    }
}
