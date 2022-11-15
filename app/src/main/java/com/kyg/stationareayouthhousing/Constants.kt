package com.kyg.stationareayouthhousing

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.viewinterop.AndroidView
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

    @Composable
    fun MyWebView(url: String) {
        var webView: WebView? = null
        var backEnable by rememberSaveable { mutableStateOf(false) }
        AndroidView(factory = {
            WebView(it).apply {
                this.webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                        backEnable = view.canGoBack()
                    }
                }
                this.settings.javaScriptEnabled = true
                this.loadUrl(url)
                webView = this
            }
        }, update = { webView = it })
        BackHandler(enabled = backEnable) {
            webView?.goBack()
        }
    }
}