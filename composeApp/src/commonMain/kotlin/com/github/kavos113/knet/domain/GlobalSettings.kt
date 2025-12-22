package com.github.kavos113.knet.domain

import com.github.kavos113.knet.lib.PathSerializer
import kotlinx.serialization.Serializable
import okio.Path

@Serializable
data class GlobalSettings(
    @Serializable(with = PathSerializer::class)
    val lastOpenedPath: Path? = null
)