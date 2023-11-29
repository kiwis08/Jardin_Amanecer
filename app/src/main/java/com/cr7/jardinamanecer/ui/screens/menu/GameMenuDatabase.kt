//package com.cr7.jardinamanecer.ui.screens.menu
//
//import android.content.Context
//import androidx.room.Dao
//import androidx.room.Database
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [(Game::class)], version = 1, exportSchema = false)
//abstract class GameMenuDatabase : RoomDatabase() {
//
//    abstract fun gameMenuDao(): GameMenuDao
//
//    companion object {
//        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
//        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
//        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/
//        @Volatile
//        private var INSTANCE: GameMenuDatabase? = null
//
//        fun getInstance(context: Context): GameMenuDatabase {
//            // only one thread of execution at a time can enter this block of code
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context,
//                        GameMenuDatabase::class.java,
//                        "game_menu_database"
//                    ).fallbackToDestructiveMigration()
//                        .build()
//
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
//}
//
//@Dao
//interface GameMenuDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(games: List<Game>)
//
//    @Query("SELECT * FROM games")
//    suspend fun getAllGames(): List<Game>
//}