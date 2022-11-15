package com.kyg.stationareayouthhousing.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kyg.stationareayouthhousing.Constants

@Composable
fun NoticeScreen(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Constants.MyWebView(url = "https://soco.seoul.go.kr/youth/bbs/BMSR00015/list.do?menuNo=400008")
    }
}