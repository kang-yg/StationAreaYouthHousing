package com.kyg.stationareayouthhousing.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.studyandroid.model.Resource
import com.example.studyandroid.model.Status
import com.kyg.stationareayouthhousing.Constants
import com.kyg.stationareayouthhousing.Constants.concatAddress
import com.kyg.stationareayouthhousing.R
import com.kyg.stationareayouthhousing.model.dto.Geocoding
import com.kyg.stationareayouthhousing.model.dto.Plan
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.*

@Composable
@ExperimentalMaterial3Api
@ExperimentalNaverMapApi
fun PlanDetail(plan: Plan?, navController: NavController, snackbarHostState: SnackbarHostState, geocodingLiveData: LiveData<Resource<Geocoding>>) {
    var targetPlace = LatLng(37.5788408, 126.9770162) // 경복궁
    val geocodingLiveDataState by geocodingLiveData.observeAsState()
    val cameraPositionState = rememberCameraPositionState { position = CameraPosition(targetPlace, 15.0) }

    if (geocodingLiveDataState != null) {
        with(geocodingLiveDataState) {
            when (this!!.status) {
                Status.SUCCESS -> {
                    this.data?.let {
                        targetPlace = LatLng(it.geocodingAddress[0].y, it.geocodingAddress[0].x)
                        cameraPositionState.move(CameraUpdate.scrollTo(targetPlace))
                    }
                    val snackbarMessage = stringResource(id = R.string.plan_detail_success)
                    Constants.showSnackBar(snackbarHostState = snackbarHostState, message = snackbarMessage)

                }

                Status.LOADING -> {
                    val snackbarMessage = stringResource(id = R.string.plan_detail_loading)
                    Constants.showSnackBar(snackbarHostState = snackbarHostState, message = snackbarMessage)

                }

                Status.ERROR -> {
                    val snackbarMessage = stringResource(id = R.string.plan_detail_error)
                    Constants.showSnackBar(snackbarHostState = snackbarHostState, message = snackbarMessage)
                }
            }
        }
    }

    Box(modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(4.dp))) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_close_24), contentDescription = null, modifier = Modifier.clickable {
                    navController.popBackStack()
                })
                Text(text = stringResource(id = R.string.plan_detail))
            }
            Column {
                plan?.let { plan ->
                    Text(text = stringResource(id = R.string.plan_detail_address, plan.address.concatAddress()))
                    NaverMap(
                        modifier = Modifier.size(300.dp, 400.dp),
                        cameraPositionState = cameraPositionState
                    ) {
                        Marker(
                            state = MarkerState(position = targetPlace),
                        )
                    }
                }
            }
        }
    }
}