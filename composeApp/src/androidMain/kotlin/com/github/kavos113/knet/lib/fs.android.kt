package com.github.kavos113.knet.lib

import android.content.Context
import okio.Path
import okio.Path.Companion.toPath

class AndroidAppConfig(private val context: Context) : AppConfig {
    override val osConfigDir: Path
        get() = context.filesDir.path.toPath()
}
