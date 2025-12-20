package com.github.kavos113.knet

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.github.kavos113.knet.ui.explorer.ExplorerScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        ExplorerScreen()
    }
}