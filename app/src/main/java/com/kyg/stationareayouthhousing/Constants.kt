package com.kyg.stationareayouthhousing

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.kyg.stationareayouthhousing.model.dto.Address

object Constants {
    const val BASE_URL = "https://naveropenapi.apigw.ntruss.com"

    fun Address.concatAddress(): String = StringBuilder().apply {
        this@apply.append(borough.plus(" "))
        this@apply.append(dong.plus(" "))
        this@apply.append(houseNumber)
    }.toString()

    @Composable
    fun showSnackBar(snackbarHostState: SnackbarHostState, message: String) {
        LaunchedEffect(key1 = this, block = {
            snackbarHostState.showSnackbar(message)
        })
    }
}