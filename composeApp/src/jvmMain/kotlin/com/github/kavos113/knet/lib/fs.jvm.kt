package com.github.kavos113.knet.lib

import okio.Path
import okio.Path.Companion.toPath

class JvmAppConfig : AppConfig {
    override val osConfigDir: Path
        get() {
            val os = System.getProperty("os.name").lowercase()
            val home = System.getProperty("user.home")

            return when {
                os.contains("win") -> {
                    System.getenv("APPDATA")?.toPath() ?: "$home\\AppData\\Roaming".toPath()
                }
                os.contains("mac") -> {
                    "$home/Library/Application Support".toPath()
                }
                else -> {
                    System.getenv("XDG_CONFIG_HOME")?.toPath() ?: "$home/.config".toPath()
                }
            }
        }
}
