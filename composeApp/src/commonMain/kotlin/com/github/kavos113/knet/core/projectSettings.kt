package com.github.kavos113.knet.core

import com.github.kavos113.knet.domain.ProjectSettings
import okio.Path

expect fun loadProjectSettings(): ProjectSettings
expect fun saveProjectSettings(settings: ProjectSettings)

expect fun saveSession(openFilePaths: List<Path>)
expect fun loadSession(): List<Path>

expect fun loadKeywords(dirPath: Path): Map<String, String>