package com.example.stationareayouthhousing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.stationareayouthhousing.view.HomeScreen
import com.example.stationareayouthhousing.view.NoticeScreen
import com.example.stationareayouthhousing.view.PlanScreen
import com.example.stationareayouthhousing.view.SupportPolicyScreen

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

            RoutName.NOTICE -> {
                if (navHostController != null && innerPadding != null)
                    NoticeScreen(navController = navHostController, innerPadding = innerPadding)
            }

            RoutName.SUPPORT_POLICY -> {
                if (navHostController != null && innerPadding != null)
                    SupportPolicyScreen(navController = navHostController, innerPadding = innerPadding)
            }
        }
    }
}