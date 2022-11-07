package com.example.stationareayouthhousing

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterial3Api
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()

            Scaffold(content = { innerPadding ->
                NavHost(navController = navController, "Home") {
                    composable("Home") {
                        RoutCompose().getScreen(routName = RoutName.HOME, navHostController = navController, innerPadding = innerPadding)
                    }

                    composable("Plan") {
                        RoutCompose().getScreen(routName = RoutName.PLAN, navHostController = navController, innerPadding = innerPadding)
                    }
                }
            })
        }
    }
}