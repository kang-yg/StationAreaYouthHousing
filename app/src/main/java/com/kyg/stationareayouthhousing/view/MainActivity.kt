package com.kyg.stationareayouthhousing.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.google.gson.GsonBuilder
import com.kyg.stationareayouthhousing.Constants
import com.kyg.stationareayouthhousing.R
import com.kyg.stationareayouthhousing.RoutName
import com.kyg.stationareayouthhousing.model.dto.Plan
import com.kyg.stationareayouthhousing.viewmodel.MainActivityViewModel
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterial3Api
@ExperimentalNaverMapApi
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
                            mainActivityViewModel.createAndReadPlan(isNetworkConnected())
                            PlanScreen(
                                navController = navController,
                                innerPadding = innerPadding,
                                planListLiveData = mainActivityViewModel.planListLiveData
                            )
                        }

                        dialog(RoutName.PLAN_DETAIL) {
                            val planJson = it.arguments?.getString(RoutName.PLAN_DETAIL_ARGUMENT_PLAN)
                            val jsonToPlan = GsonBuilder().setLenient().create().fromJson(planJson, Plan::class.java)
                            if (isNetworkConnected()) {
                                mainActivityViewModel.getGeocodingAddress(jsonToPlan.address)
                            } else {
                                Constants.showSnackBar(snackbarHostState = snackbarHostState, message = stringResource(id = R.string.no_network))
                            }
                            PlanDetail(
                                plan = jsonToPlan,
                                navController = navController,
                                snackbarHostState = snackbarHostState,
                                geocodingLiveData = mainActivityViewModel.geocodingLiveData
                            )
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

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val caps = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
        return caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }
}