package com.example.stationareayouthhousing.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.stationareayouthhousing.R
import com.example.stationareayouthhousing.model.dto.Plan

@Composable
fun PlanDetail(innerPadding: PaddingValues, plan: Plan?) {
    Box(modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(4.dp))) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_close_24), contentDescription = null)
                Text(text = stringResource(id = R.string.plan_detail))
            }
            Column {
                
            }
        }
    }
}