package com.example.stationareayouthhousing.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stationareayouthhousing.RoutCompose
import com.example.stationareayouthhousing.RoutName
import com.example.stationareayouthhousing.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterial3Api
class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    lateinit var navController: NavHostController
    lateinit var snackbarHostState: SnackbarHostState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            snackbarHostState = remember { SnackbarHostState() }
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                content = { innerPadding ->
                    NavHost(navController = navController, RoutName.HOME) {
                        composable(RoutName.HOME) {
                            RoutCompose().getScreen(routName = RoutName.HOME, navHostController = navController, innerPadding = innerPadding)
                        }

                        composable(RoutName.PLAN) {
                            RoutCompose().getScreen(routName = RoutName.PLAN, navHostController = navController, innerPadding = innerPadding)
                            mainActivityViewModel.crawlingPlan()
                        }

                        composable(RoutName.NOTICE) {
                            RoutCompose().getScreen(routName = RoutName.NOTICE, navHostController = navController, innerPadding = innerPadding)
                        }

                        composable(RoutName.SUPPORT_POLICY) {
                            RoutCompose().getScreen(
                                routName = RoutName.SUPPORT_POLICY,
                                navHostController = navController,
                                innerPadding = innerPadding
                            )
                        }
                    }
                }
            )
        }
    }
}