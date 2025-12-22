package com.github.kavos113.knet.core

import com.github.kavos113.knet.domain.OpenedPaths
import com.github.kavos113.knet.domain.ProjectSettings
import kotlinx.serialization.json.Json
import okio.FileSystem
import okio.Path

private const val PROJECT_SETTINGS_DIR = ".knet"
private const val PROJECT_SETTINGS_FILE = "settings.json"
private const val PROJECT_SESSION_FILE = "session.json"
private const val PROJECT_KEYWORDS_FILE = "keywords.json"

expect fun loadProjectSettings(): ProjectSettings
expect fun saveProjectSettings(settings: ProjectSettings)

expect fun saveSession(openFilePaths: List<Path>)
expect fun loadSession(): List<Path>

expect fun loadKeywords(): Map<String, String>

class ProjectSettingsManager(
    private val fileSystem: FileSystem,
) {
    var rootDir: Path? = null
        set(value) {
            field = value

            field?.let {
                val settingsDir = it / PROJECT_SETTINGS_DIR
                if (!fileSystem.exists(settingsDir)) {
                    fileSystem.createDirectory(settingsDir)
                }

                val settingsPath = settingsDir / PROJECT_SETTINGS_FILE
                val sessionPath = settingsDir / PROJECT_SESSION_FILE
                val keywordsPath = settingsDir / PROJECT_KEYWORDS_FILE

                if (!fileSystem.exists(settingsPath)) {
                    fileSystem.write(settingsPath) { }
                }
                if (!fileSystem.exists(sessionPath)) {
                    fileSystem.write(sessionPath) { }
                }
                if (!fileSystem.exists(keywordsPath)) {
                    fileSystem.write(keywordsPath) { }
                }
            }
        }

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    fun saveSettings(settings: ProjectSettings) {
        rootDir?.let {
            fileSystem.write(it / PROJECT_SETTINGS_DIR / PROJECT_SETTINGS_FILE) {
                writeUtf8(json.encodeToString(ProjectSettings.serializer(), settings))
            }
        }
    }

    fun loadSettings(): ProjectSettings {
        return rootDir?.let {
            if (fileSystem.exists(it / PROJECT_SETTINGS_DIR / PROJECT_SETTINGS_FILE)) {
                val jsonString = fileSystem.read(it / PROJECT_SETTINGS_DIR / PROJECT_SETTINGS_FILE) {
                    readUtf8()
                }
                json.decodeFromString(ProjectSettings.serializer(), jsonString)
            } else {
                ProjectSettings()
            }
        } ?: ProjectSettings()
    }

    fun saveSession(openFilePaths: OpenedPaths) {
        rootDir?.let {
            fileSystem.write(it / PROJECT_SETTINGS_DIR / PROJECT_SESSION_FILE) {
                writeUtf8(json.encodeToString(OpenedPaths.serializer(), openFilePaths))
            }
        }
    }

    fun loadSession(): OpenedPaths {
        return rootDir?.let {
            if (fileSystem.exists(it / PROJECT_SETTINGS_DIR / PROJECT_SESSION_FILE)) {
                val jsonString = fileSystem.read(it / PROJECT_SETTINGS_DIR / PROJECT_SESSION_FILE) {
                    readUtf8()
                }
                json.decodeFromString(OpenedPaths.serializer(), jsonString)
            } else {
                OpenedPaths()
            }
        } ?: OpenedPaths()
    }
}