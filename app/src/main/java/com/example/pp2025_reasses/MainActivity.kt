package com.example.pp2025_reasses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.pp2025_reasses.ui.theme.ActiveTheme
import com.example.pp2025_reasses.ui.theme.PP2025_ReassesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            val viewModel: ATD_ViewModel by viewModels()
            val isDark by viewModel.isDark.collectAsState()
            val isContrast by viewModel.isContrast.collectAsState()


            val themeMode = when {
                isContrast -> ActiveTheme.HIGH_CONTRAST
                isDark -> ActiveTheme.DARK
                else -> ActiveTheme.LIGHT
            }

            PP2025_ReassesTheme(themeMode = themeMode ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   AppNavigation(
                       viewModel = viewModel
                   )
                }
            }
        }
    }


}


