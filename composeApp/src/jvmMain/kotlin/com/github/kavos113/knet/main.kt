package com.github.kavos113.knet

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "knet",
    ) {
        App()
    }
}