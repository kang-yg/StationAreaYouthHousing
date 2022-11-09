package com.example.stationareayouthhousing.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.stationareayouthhousing.R
import com.example.stationareayouthhousing.model.dto.Plan

@Composable
fun PlanScreen(navController: NavController, innerPadding: PaddingValues, planListLiveData: LiveData<List<Plan>>) {
    val planListLivaDataState by planListLiveData.observeAsState()
    Box(modifier = Modifier.padding(innerPadding)) {
        Column {
            planListLivaDataState?.let { PlanLazyColumn(it) }
        }
    }
}

@Composable
fun PlanItem(plan: Plan) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column {
            Row {
                Text(text = plan.station, fontWeight = FontWeight.Bold)
                Text(text = stringResource(id = R.string.address, plan.address.borough, plan.address.dong, plan.address.houseNumber))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(id = R.string.schedule_public))
                    Text(text = plan.publicDueDate)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(id = R.string.schedule_private))
                    Text(text = plan.privateDueDate)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(id = R.string.schedule_move_in))
                    Text(text = plan.expectedMoveInDate)
                }
            }
        }
    }
}

@Composable
fun PlanLazyColumn(planList: List<Plan>) {
    LazyColumn {
        itemsIndexed(items = planList) { index, item ->
            PlanItem(plan = item)
        }
    }
}
