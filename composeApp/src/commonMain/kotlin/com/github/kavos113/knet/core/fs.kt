package com.github.kavos113.knet.core

import com.github.kavos113.knet.domain.FileItem
import okio.Path

expect fun getFileTree(path: Path): List<FileItem>
expect fun readFile(path: Path): ByteArray
expect fun writeFile(path: Path, data: ByteArray)
expect fun createFile(path: Path)
expect fun createDirectory(path: Path)

expect fun getKeywordContent(path: Path, keyword: String): String?