package com.example.pp2025_reasses

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pp2025_reasses.pages.LocationPage
import com.example.pp2025_reasses.pages.LoginPage
import com.example.pp2025_reasses.pages.SettingsPage


@Composable
    fun AppNavigation(
        viewModel: ATD_ViewModel,
        navController: NavHostController = rememberNavController()
    )
    {

            NavHost(
                navController = navController,
                startDestination = LocationTrackingApp.Login.name,
                modifier = Modifier.padding(all = 10.dp)
            ) {

                composable(route = LocationTrackingApp.Login.name) {
                    val isAuthenticated by viewModel.isAuthenticated.collectAsState()
                    if (isAuthenticated)
                    {
                        navController.navigate(LocationTrackingApp.Location.name)
                    }
                    else{
                        LoginPage(
                            onLogin = { viewModel.checkPassword(it) },
                            onFirst = { viewModel.savePassword(it) }
                        )
                    }
                }

                composable(route = LocationTrackingApp.Location.name) {
                    LocationPage(
                        onSettingsClick = {navController.navigate(LocationTrackingApp.Setting.name)},
                        viewModel = viewModel
                    )

                }
                composable(route = LocationTrackingApp.Setting.name) {
                    SettingsPage(
                        viewModel = viewModel,
                        onBack = {navController.navigate(LocationTrackingApp.Location.name)}
                    )

                }

            }
    }

    enum class LocationTrackingApp()
    {
        Login,
        Location,
        Setting
    }
