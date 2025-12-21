package com.github.kavos113.knet.core

import com.github.kavos113.knet.lib.OsConfig
import okio.FileSystem
import okio.Path

private const val GLOBAL_SETTINGS_DIR = "knet"
private const val GLOBAL_SETTINGS_FILE = "settings.json"

class GlobalSettingsManager(
    private val fileSystem: FileSystem = FileSystem.SYSTEM,
    private val osConfig: OsConfig
) {
    init {
        val configDir = osConfig.osConfigDir / GLOBAL_SETTINGS_DIR

        if (!fileSystem.exists(configDir)) {
            fileSystem.createDirectory(configDir)
        }

        val settingsPath = configDir / GLOBAL_SETTINGS_FILE
        if (!fileSystem.exists(settingsPath)) {
            fileSystem.write(settingsPath) { }
        }
    }
}