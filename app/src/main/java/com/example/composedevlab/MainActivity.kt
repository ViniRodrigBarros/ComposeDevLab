package com.example.composedevlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composedevlab.features.AppNavigation
import com.example.composedevlab.ui.theme.ComposeDevLabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDevLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Usamos um Box ou apenas passamos o padding se necessário, 
                    // mas o NavHost geralmente gerencia suas próprias telas.
                    AppNavigation()
                }
            }
        }
    }
}
