package com.example.stationareayouthhousing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, innerPadding: PaddingValues) {
    Box(modifier = Modifier.padding(innerPadding)) {
        Column() {
            Text(text = "This is home.")
            Button(onClick = { navController.navigate("Plan") }) {
                Text(text = "Go to Plan.")
            }
        }
    }
}