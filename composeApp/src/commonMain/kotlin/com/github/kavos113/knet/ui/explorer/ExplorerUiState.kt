package com.github.kavos113.knet.ui.explorer

import com.github.kavos113.knet.domain.FileItem
import okio.Path

data class ExplorerUiState(
    val files: List<FileItem> = emptyList(),
    val openedPaths: Set<Path> = emptySet()
)
