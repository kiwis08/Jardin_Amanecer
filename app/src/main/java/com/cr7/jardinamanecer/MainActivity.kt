package com.cr7.jardinamanecer

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.datastore.dataStore
import androidx.navigation.compose.rememberNavController
import com.cr7.jardinamanecer.navigation.NavGraph
import com.cr7.jardinamanecer.ui.AppSettingsSerializer
import com.cr7.jardinamanecer.ui.screens.AppSettings
import com.cr7.jardinamanecer.ui.theme.JardinAmanecerTheme


val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JardinAmanecerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val currentStudent = dataStore.data.collectAsState(initial = AppSettings()).value.currentStudent
                    NavGraph(navController = navController, sessionSaved = currentStudent != null)
                }
            }
        }
    }
}