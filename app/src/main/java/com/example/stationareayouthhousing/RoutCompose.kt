package com.example.stationareayouthhousing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

class RoutCompose {
    @Composable
    fun getScreen(routName: String, navHostController: NavHostController? = null, innerPadding: PaddingValues? = null) {
        when (routName) {
            RoutName.HOME -> {
                if (navHostController != null && innerPadding != null)
                    HomeScreen(navController = navHostController, innerPadding = innerPadding)
            }

            RoutName.PLAN -> {
                if (navHostController != null && innerPadding != null)
                    PlanScreen(navController = navHostController, innerPadding = innerPadding)
            }
        }
    }
}