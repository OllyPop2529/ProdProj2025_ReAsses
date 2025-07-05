package com.example.pp2025_reasses

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pp2025_reasses.pages.LocationPage
import com.example.pp2025_reasses.pages.SettingsPage
import com.example.productionproject.LoginPage


    @Composable
    fun AppNavigation(
        //viewModel: OrderViewModel = viewModel(),
        navController: NavHostController = rememberNavController()
    )
    {
            //val uiState by viewModel.uiState.collectAsState()
            NavHost(
                navController = navController,
                startDestination = LocationTrackingApp.Login.name,
                modifier = Modifier.padding(all = 10.dp)
            ) {

                composable(route = LocationTrackingApp.Login.name) {
                    LoginPage()

                }

                composable(route = LocationTrackingApp.Location.name) {
                    LocationPage()

                }
                composable(route = LocationTrackingApp.Setting.name) {
                    SettingsPage()

                }

            }
    }

    enum class LocationTrackingApp()
    {
        Login,
        Location,
        Setting
    }
