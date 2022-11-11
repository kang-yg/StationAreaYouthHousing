package com.kyg.stationareayouthhousing.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kyg.stationareayouthhousing.R
import com.kyg.stationareayouthhousing.RoutName

@Composable
fun HomeScreen(navController: NavController, innerPadding: PaddingValues) {
    Box(modifier = Modifier.padding(innerPadding)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
/*            AsyncImage(
                model = "https://soco.seoul.go.kr/resources/youth/img/main/youth_logo_color.png",
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )*/
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                onClick = { navController.navigate(RoutName.PLAN) }
            ) {
                Text(text = stringResource(id = R.string.go_plan_screen))
            }
            Button(modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                onClick = { navController.navigate(RoutName.NOTICE) }) {
                Text(text = stringResource(id = R.string.go_notice_screen))
            }
            Button(modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                onClick = { navController.navigate(RoutName.SUPPORT_POLICY) }) {
                Text(text = stringResource(id = R.string.go_supportPolicy_screen))
            }
        }
    }
}