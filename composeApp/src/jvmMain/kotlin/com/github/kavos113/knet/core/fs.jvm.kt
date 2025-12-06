package com.github.kavos113.knet.core

import com.github.kavos113.knet.domain.FileItem
import okio.Path

actual fun getFileTree(path: Path): List<FileItem> {
    TODO("Not yet implemented")
}

actual fun readFile(path: Path): ByteArray {
    TODO("Not yet implemented")
}

actual fun writeFile(path: Path, data: ByteArray) {
}

actual fun createFile(path: Path) {
}

actual fun createDirectory(path: Path) {
}

actual fun getKeywordContent(path: Path, keyword: String): String? {
    TODO("Not yet implemented")
}