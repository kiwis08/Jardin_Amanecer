package com.cr7.jardinamanecer.ui.screens.level4.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cr7.jardinamanecer.dataStore
import com.cr7.jardinamanecer.ui.screens.level4.localdatabase.CommunicatorDatabase
import com.cr7.jardinamanecer.ui.screens.level4.model.CommunicatorCategory
import com.cr7.jardinamanecer.ui.screens.level4.model.ComunicatorItem
import com.cr7.jardinamanecer.ui.screens.level4.repository.CommunicatorRepository
import com.cr7.jardinamanecer.ui.screens.level4.state.CommunicatorState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Locale

class CommunicatorViewModel(application: Application): AndroidViewModel(application) {

    private val communicatorRepository: CommunicatorRepository


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

    suspend fun _getItems(onCompletion: () -> Unit = {}) {
        try {
            val snapshot = database.collection("level4").document(userID).get(Source.SERVER).await()
            val data = snapshot.data ?: return
            val communicatorItems = data["communicatorItems"] as List<Map<String, String>>
            val items = communicatorItems.map { item ->
                ComunicatorItem(
                    title = item["title"]!!,
                    category = item["category"]!!,
                    imageName = item["imageName"]!!,
                )
            }
            println("Got items from firebase")
            _state.value.items = items
            communicatorRepository.saveCommunicatorItems(items)
        } catch (e: Exception) {
            println("Got items from local database")
            val items = communicatorRepository.getAllCommunicatorItems()
            _state.value.items = items
        }
    }

    suspend fun _getItemsImagesUrl() {
        try {
            val items = state.value.items
            items.forEach { item ->
                val data = storage.reference.child("$userID/${item.category}/${item.imageName}").downloadUrl.await()
                val url = data.toString()
                _state.value.items.find {
                    it.title == item.title
                }.let {
                    it?.imageUrl = url
                }
                val imageData = storage.reference.child("$userID/${item.category}/${item.imageName}").getBytes(Long.MAX_VALUE).await()
                val localPath = getApplication<Application>().filesDir.path + "/${item.imageName}"
                _state.value.items.find {
                    it.title == item.title
                }.let {
                    it?.imageLocalPath = localPath
                }
                getApplication<Application>().openFileOutput(item.imageName, Context.MODE_PRIVATE).use {
                    it.write(imageData)
                }
                communicatorRepository.updateItemImagePath(item.title, localPath)
            }
        } catch (e: Exception) {
            println("Got images from local database")
        }

    }

    suspend fun _getCategories() {
//        database.collection("level4").document("general").get()
//            .addOnSuccessListener { result ->
//                val data = result.data ?: return@addOnSuccessListener
//                val categories = data["communicator_categories"] as List<String>
//                _state.value.categories = categories.map { it.lowercase() }
//            }
        try {
            val snapshot = database.collection("level4").document("general").get(Source.SERVER).await()
            val categoriesString = snapshot.data?.get("communicator_categories") as List<String>
            val categories = categoriesString.map { CommunicatorCategory(it) }
            println("Got categories from firebase")
            communicatorRepository.saveCategories(categories)
            _state.value.categories = categoriesString.map { it.lowercase() }
        } catch (e: Exception) {
            println("Got categories from local database")
            val categories = communicatorRepository.getAllCategories()
            _state.value.categories = categories.map { it.title.lowercase() }
        }
    }

    init {
        val communicatorDao = CommunicatorDatabase.getInstance(application).communicatorDao()
        communicatorRepository = CommunicatorRepository(communicatorDao)
        viewModelScope.launch {
            _getCategories()
            _getItems()
            _getItemsImagesUrl()
        }
    }

    fun checkInternetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    }
}