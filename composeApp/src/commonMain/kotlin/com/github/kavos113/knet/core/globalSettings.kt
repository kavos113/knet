package com.github.kavos113.knet.core

import okio.FileSystem
import okio.Path

expect fun getLastOpened(): Path?
expect fun setLastOpened(path: Path)

private const val GLOBAL_SETTINGS_DIR = "knet"
private const val GLOBAL_SETTINGS_FILE = "settings.json"

class GlobalSettingsManager(private val fileSystem: FileSystem = FileSystem.SYSTEM) {
    init {

    }
}