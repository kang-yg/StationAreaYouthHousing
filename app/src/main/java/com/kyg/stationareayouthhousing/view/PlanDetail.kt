package com.kyg.stationareayouthhousing.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kyg.stationareayouthhousing.R
import com.kyg.stationareayouthhousing.model.dto.Plan
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap

@Composable
@ExperimentalMaterial3Api
@ExperimentalNaverMapApi
fun PlanDetail(innerPadding: PaddingValues, plan: Plan?) {
    var searchKeyWord by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(4.dp))) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_close_24), contentDescription = null)
                Text(text = stringResource(id = R.string.plan_detail))
            }
            Column {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = searchKeyWord,
                    onValueChange = { searchKeyWord = it },
                    label = { Text(text = stringResource(id = R.string.plan_search)) })
                NaverMap(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}