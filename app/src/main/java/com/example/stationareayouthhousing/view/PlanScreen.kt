package com.example.stationareayouthhousing.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.stationareayouthhousing.R
import com.example.stationareayouthhousing.RoutName
import com.example.stationareayouthhousing.model.dto.Plan
import com.google.gson.Gson

@Composable
fun PlanScreen(navController: NavController, innerPadding: PaddingValues, planListLiveData: LiveData<List<Plan>>) {
    val planListLivaDataState by planListLiveData.observeAsState()
    var planListForFilter by rememberSaveable { mutableStateOf(planListLivaDataState) }

    Box(modifier = Modifier.padding(innerPadding)) {
        Column {
            planListLivaDataState?.let {
                if (it.isEmpty()) {
                    // TODO Show empty screen
                } else {
                    Row {
                        var selectYear by rememberSaveable { mutableStateOf("") }
                        var selectBorough by rememberSaveable { mutableStateOf("") }
                        Text(text = stringResource(id = R.string.plan_filter))
                        Box(modifier = Modifier.padding(0.dp, 0.dp, 4.dp, 0.dp))
                        DropdownFilter(getSpinnerYear(it)) { year ->
                            selectYear = year
                            planListForFilter = filterPlan(it, if (selectYear != "ALL") selectYear else "", if (selectBorough != "ALL") selectBorough else "")
                        }
                        Box(modifier = Modifier.padding(0.dp, 0.dp, 4.dp, 0.dp))
                        DropdownFilter(getSpinnerBorough(it)) { borough ->
                            selectBorough = borough
                            planListForFilter = filterPlan(it, if (selectYear != "ALL") selectYear else "", if (selectBorough != "ALL") selectBorough else "")
                        }
                    }
                    if (planListForFilter == null)
                        PlanLazyColumn(planList = it, onClick = { plan -> planItemClickEvent(navController, plan) })
                    else
                        PlanLazyColumn(planList = planListForFilter!!, onClick = { plan -> planItemClickEvent(navController, plan) })
                }
            }
        }
    }
}

@Composable
fun DropdownFilter(items: List<String>, selectItem: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    Box(
        modifier = Modifier.border(width = 1.dp, color = Color.Black, shape = RectangleShape)
    ) {
        Text(
            items[selectedIndex],
            modifier = Modifier.clickable(onClick = { expanded = true })
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    selectItem(items[selectedIndex])
                }, text = { Text(text = s) })
            }
        }
    }
}

@Composable
fun PlanItem(plan: Plan, onClick: (Plan) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(plan) }
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
fun PlanLazyColumn(planList: List<Plan>, onClick: (Plan) -> Unit) {
    LazyColumn {
        itemsIndexed(items = planList) { index, item ->
            PlanItem(plan = item, onClick = onClick)
        }
    }
}

private fun getSpinnerYear(planList: List<Plan>): List<String> {
    return planList.map { it.year }.distinct().toMutableList().apply { add(0, "ALL") }
}

private fun getSpinnerBorough(planList: List<Plan>): List<String> {
    return planList.map { it.address.borough }.distinct().toMutableList().apply { add(0, "ALL") }
}

private fun filterPlan(planList: List<Plan>, year: String, borough: String): List<Plan> {
    return if (year.isNotEmpty() && borough.isNotEmpty()) {
        planList.filter { it.year == year }.filter { it.address.borough == borough }
    } else if (year.isNotEmpty()) {
        planList.filter { it.year == year }
    } else if (borough.isNotEmpty()) {
        planList.filter { it.address.borough == borough }
    } else {
        planList
    }
}

private fun planItemClickEvent(navController: NavController, plan: Plan) {
    val planToJson = Gson().toJson(plan)
    navController.navigate(RoutName.PLAN_DETAIL.replace(oldValue = "{${RoutName.PLAN_DETAIL_ARGUMENT}}", newValue = planToJson))
}
