package com.github.kavos113.knet.domain

const val PROJECT_SETTINGS_DIR = ".knet"
const val PROJECT_SETTINGS_FILE = "settings.json"
const val PROJECT_SESSION_FILE = "session.json"
const val PROJECT_KEYWORDS_FILE = "keywords.json"

data class ProjectSettings(
    val settings: FontSettings
)

data class FontSettings(
    val editorFontFamily: String,
    val editorFontSize: Int,
    val previewFontFamily: String,
    val previewFontSize: Int,
)