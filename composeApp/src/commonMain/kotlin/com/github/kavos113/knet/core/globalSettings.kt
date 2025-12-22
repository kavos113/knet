package com.github.kavos113.knet.core

import com.github.kavos113.knet.domain.GlobalSettings
import com.github.kavos113.knet.lib.OsConfig
import kotlinx.serialization.json.Json
import okio.FileSystem
import okio.Path

private const val GLOBAL_SETTINGS_DIR = "knet"
private const val GLOBAL_SETTINGS_FILE = "settings.json"

class GlobalSettingsManager(
    private val fileSystem: FileSystem = FileSystem.SYSTEM,
    private val osConfig: OsConfig
) {

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    private val settingsPath: Path

    init {
        val configDir = osConfig.osConfigDir / GLOBAL_SETTINGS_DIR

        if (!fileSystem.exists(configDir)) {
            fileSystem.createDirectory(configDir)
        }

        settingsPath = configDir / GLOBAL_SETTINGS_FILE
        if (!fileSystem.exists(settingsPath)) {
            fileSystem.write(settingsPath) { }
        }
    }

    fun save(settings: GlobalSettings) {
        fileSystem.write(settingsPath) {
            writeUtf8(json.encodeToString(GlobalSettings.serializer(), settings))
        }
    }

    fun load(): GlobalSettings {
        return if (fileSystem.exists(settingsPath)) {
            val jsonString = fileSystem.read(settingsPath) {
                readUtf8()
            }
            json.decodeFromString(GlobalSettings.serializer(), jsonString)
        } else {
            GlobalSettings()
        }
    }
}