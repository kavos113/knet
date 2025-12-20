package com.github.kavos113.knet

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.github.kavos113.knet.di.appModule
import com.github.kavos113.knet.di.jvmModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(jvmModule, appModule)
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "knet",
        ) {
            App()
        }
    }
}