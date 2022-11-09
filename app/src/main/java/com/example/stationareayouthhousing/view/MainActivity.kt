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
                            HomeScreen(navController = navController, innerPadding = innerPadding)
                        }

                        composable(RoutName.PLAN) {
                            mainActivityViewModel.crawlingPlan()
                            mainActivityViewModel.getAllPlan()
                            PlanScreen(navController = navController, innerPadding = innerPadding, planListLiveData = mainActivityViewModel.planListLiveData)
                        }

                        composable(RoutName.NOTICE) {
                            NoticeScreen(navController = navController, innerPadding = innerPadding)
                        }

                        composable(RoutName.SUPPORT_POLICY) {
                            SupportPolicyScreen(navController = navController, innerPadding = innerPadding)
                        }
                    }
                }
            )
        }
    }
}