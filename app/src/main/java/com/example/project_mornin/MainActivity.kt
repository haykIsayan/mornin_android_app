package com.example.project_mornin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.project_mornin.features.mornin.MorninScreen
import com.example.project_mornin.ui.theme.LegacyMorninTheme
import com.example.project_mornin.ui.theme.MorninTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MorninTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                ) { values ->
                    MorninScreen()
                }
            }
        }
    }
}

