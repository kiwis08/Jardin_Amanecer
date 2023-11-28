package com.cr7.jardinamanecer.ui.screens.level4.localdatabase

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.cr7.jardinamanecer.ui.screens.level4.model.CommunicatorCategory
import com.cr7.jardinamanecer.ui.screens.level4.model.ComunicatorItem


@Database(entities = [(ComunicatorItem::class), (CommunicatorCategory::class)], version = 4, exportSchema = false)
abstract class CommunicatorDatabase : RoomDatabase() {

    abstract fun communicatorDao(): ComunicatorItemDao

    companion object {
        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/
        @Volatile
        private var INSTANCE: CommunicatorDatabase? = null

        fun getInstance(context: Context): CommunicatorDatabase {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        CommunicatorDatabase::class.java,
                        "communicator_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

@Dao
interface ComunicatorItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(communicatorItems: List<ComunicatorItem>)

    @Query("SELECT * FROM communicator_item")
    suspend fun getAllCommunicatorItems(): List<ComunicatorItem>

    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<CommunicatorCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(categories: List<CommunicatorCategory>)

    @Query("UPDATE communicator_item SET imageLocalPath = :imagePath WHERE title = :title")
    suspend fun updateItemImagePath(title: String, imagePath: String)
}