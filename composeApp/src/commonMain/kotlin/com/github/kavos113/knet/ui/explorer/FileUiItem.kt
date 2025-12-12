package com.github.kavos113.knet.ui.explorer

import com.github.kavos113.knet.domain.FileItem
import okio.Path

data class FileUiItem(
    val name: String,
    val path: Path,
    val isOpen: Boolean,
    val level: Int,
    val type: ItemType
)

enum class ItemType {
    File,
    Directory
}

fun FileItem.toUiItem(level: Int) = when(this) {
    is FileItem.DirectoryEntry -> FileUiItem(
        name = name,
        path = path,
        isOpen = false,
        level = level,
        type = ItemType.Directory
    )
    is FileItem.FileEntry -> FileUiItem(
        name = name,
        path = path,
        isOpen = false,
        level = level,
        type = ItemType.File
    )
}