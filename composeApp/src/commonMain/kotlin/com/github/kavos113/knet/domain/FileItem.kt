package com.github.kavos113.knet.domain

import okio.Path

sealed class FileItem {
    data class FileEntry(
        val name: String,
        val path: Path,
        val size: Long,
    ) : FileItem()

    data class DirectoryEntry(
        val name: String,
        val path: Path,
        val children: List<FileItem>
    ) : FileItem()
}
