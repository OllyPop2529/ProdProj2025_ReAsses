package com.example.pp2025_reasses

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pp2025_reasses.pages.LocationPage
import com.example.pp2025_reasses.pages.SettingsPage
import com.example.pp2025_reasses.pages.LoginPage


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
                    var isAuthenticated by rememberSaveable { mutableStateOf(false) }
                    if (isAuthenticated)
                    {
                        navController.navigate(LocationTrackingApp.Location.name)
                    }
                    else{
                        LoginPage(
                            onAuthentication = { isAuthenticated = true }
                        )
                    }
                }

                composable(route = LocationTrackingApp.Location.name) {
                    LocationPage(
                        onSettingsClick = { navController.navigate(LocationTrackingApp.Setting.name)}
                    )

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
