package com.cr7.jardinamanecer.ui.screens.level4.repository

import androidx.lifecycle.MutableLiveData
import com.cr7.jardinamanecer.ui.screens.level4.localdatabase.ComunicatorItemDao
import com.cr7.jardinamanecer.ui.screens.level4.model.CommunicatorCategory
import com.cr7.jardinamanecer.ui.screens.level4.model.ComunicatorItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommunicatorRepository(private val communicatorDao: ComunicatorItemDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    suspend fun saveCommunicatorItems(communicatorItems: List<ComunicatorItem>) {
        coroutineScope.launch {
            communicatorDao.insertAll(communicatorItems)
        }
    }

    suspend fun getAllCommunicatorItems(): List<ComunicatorItem> {
        return communicatorDao.getAllCommunicatorItems()
    }

    suspend fun getAllCategories(): List<CommunicatorCategory> {
        return communicatorDao.getAllCategories()
    }

    suspend fun saveCategories(categories: List<CommunicatorCategory>) {
        coroutineScope.launch {
            communicatorDao.insertAllCategories(categories)
        }
    }

    suspend fun updateItemImagePath(title: String, imagePath: String) {
        coroutineScope.launch {
            communicatorDao.updateItemImagePath(title, imagePath)
        }
    }
}