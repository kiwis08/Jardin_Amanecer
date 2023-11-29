package com.cr7.jardinamanecer.ui.screens.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow

class StudentProfileViewModel: ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    fun getStudentProfile(id: String): Flow<Student?> = firestore.collection("users")
        .document(id)
        .dataObjects()

    fun enableGameForStudent(studentId: String, gameLevel: Int, gameNumber: Int) {
        // Add number to list inside of map. Keep other values in map
        val db = firestore.collection("users")
        val docRef = db.document(studentId)
        docRef.get().addOnSuccessListener { document ->
            // Check if the games field exists
            if (document.contains("games")) {
                val gamesMap = document.get("games") as Map<String, List<Int>>

                // Update the specified level with the new game number
                val updatedGamesMap = gamesMap.toMutableMap()
                val levelKey = "level$gameLevel"
                if (gamesMap.containsKey(levelKey)) {
                    // Get the existing game numbers for the level
                    val existingGameNumbers = gamesMap[levelKey] as List<Int>

                    // Create a new list with the existing numbers and the new number
                    val updatedGameNumbers = existingGameNumbers.toMutableList()
                    updatedGameNumbers.add(gameNumber)

                    // Update the map with the updated game numbers
                    updatedGamesMap[levelKey] = updatedGameNumbers
                } else {
                    // Add the new game number for the level
                    updatedGamesMap[levelKey] = listOf(gameNumber)
                }

                // Update the document with the updated games map
                docRef.update("games", updatedGamesMap)
            } else {
                // Initialize the games map with the new game number
                docRef.update("games", mapOf("level$gameLevel" to listOf(gameNumber)))
            }
        }.addOnFailureListener { exception ->
            Log.w("enableGameForStudent", "Error updating games for student", exception)
        }
    }

    fun disableGameForStudent(studentId: String, gameLevel: Int, gameNumber: Int) {
        // Get the current games map for the student
        val db = firestore.collection("users")
        val docRef = db.document(studentId)
        docRef.get().addOnSuccessListener { document ->
            // Check if the games field exists
            if (document.contains("games")) {
                val gamesMap = document.get("games") as Map<String, List<Int>>

                // Update the specified level by removing the game number
                val updatedGamesMap = gamesMap.toMutableMap()
                val levelKey = "level$gameLevel"
                if (gamesMap.containsKey(levelKey)) {
                    // Get the existing game numbers for the level
                    val existingGameNumbers = gamesMap[levelKey] as List<Int>

                    // Create a new list without the game number
                    val updatedGameNumbers = existingGameNumbers.toMutableList()
                    updatedGameNumbers.removeAll { it == gameNumber }

                    // Update the map if there are still game numbers for the level
                    if (updatedGameNumbers.isNotEmpty()) {
                        updatedGamesMap[levelKey] = updatedGameNumbers
                    } else {
                        // Remove the level if there are no more game numbers
                        updatedGamesMap.remove(levelKey)
                    }
                }

                // Update the document with the updated games map
                docRef.update("games", updatedGamesMap)
            }
        }.addOnFailureListener { exception ->
            Log.w("disableGameForStudent", "Error updating games for student", exception)
        }
    }

}