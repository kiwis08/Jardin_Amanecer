//package com.cr7.jardinamanecer.ui.screens.menu
//
//import com.cr7.jardinamanecer.ui.screens.level4.localdatabase.ComunicatorItemDao
//import com.cr7.jardinamanecer.ui.screens.level4.model.CommunicatorCategory
//import com.cr7.jardinamanecer.ui.screens.level4.model.ComunicatorItem
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class GameMenuRepository(private val gameMenuDao: GameMenuDao) {
//
//    private val coroutineScope = CoroutineScope(Dispatchers.IO)
//
//
//    suspend fun saveGames(games: List<Game>) {
//        coroutineScope.launch {
//            gameMenuDao.insertAll(games)
//        }
//    }
//
//    suspend fun getAllGames(): List<Game> {
//        return gameMenuDao.getAllGames()
//    }
//}