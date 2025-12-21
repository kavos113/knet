package com.github.kavos113.knet.lib

import android.content.Context
import okio.Path
import okio.Path.Companion.toPath

class AndroidOsConfig(private val context: Context) : OsConfig {
    override val osConfigDir: Path
        get() = context.filesDir.path.toPath()
}
