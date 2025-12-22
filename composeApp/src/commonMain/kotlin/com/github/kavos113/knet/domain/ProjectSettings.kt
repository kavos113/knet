package com.github.kavos113.knet.domain

import com.github.kavos113.knet.lib.PathListSerializer
import kotlinx.serialization.Serializable
import okio.Path


@Serializable
data class ProjectSettings(
    val settings: FontSettings = FontSettings()
)

@Serializable
data class FontSettings(
    val editorFontFamily: String = "",
    val editorFontSize: Int = 0,
    val previewFontFamily: String = "",
    val previewFontSize: Int = 0,
)

@Serializable
data class OpenedPaths(
    @Serializable(with = PathListSerializer::class)
    val paths: List<Path> = emptyList()
)