package com.github.kavos113.knet.core

import com.github.kavos113.knet.domain.FileItem
import okio.FileSystem
import okio.Path

class FileManager(private val fileSystem: FileSystem = FileSystem.SYSTEM) {
    private val keywordRegex = Regex("""<keyword name="([^>"]*)">([\s\S]*?)</keyword>""")

    fun getFileTree(path: Path): List<FileItem> {
        val fileItems = mutableListOf<FileItem>()
        if (!fileSystem.exists(path)) return fileItems

        fileSystem.list(path).forEach { childPath ->
            val isDirectory = fileSystem.metadata(childPath).isDirectory
            val item = if (isDirectory) {
                val children = getFileTree(childPath)
                FileItem.DirectoryEntry(
                    name = childPath.name,
                    path = childPath,
                    children = children
                )
            } else {
                val size = fileSystem.metadata(childPath).size ?: 0L
                FileItem.FileEntry(
                    name = childPath.name,
                    path = childPath,
                    size = size
                )
            }
            fileItems.add(item)
        }
        return fileItems
    }

    fun readFile(path: Path): String {
        return fileSystem.read(path) {
            readUtf8()
        }
    }

    fun writeFile(path: Path, data: String) {
        fileSystem.write(path) {
            writeUtf8(data)
        }
    }

    fun readBinaryFile(path: Path): ByteArray {
        return fileSystem.read(path) {
            readByteArray()
        }
    }

    fun writeBinaryFile(path: Path, data: ByteArray) {
        fileSystem.write(path) {
            write(data)
        }
    }

    fun createFile(path: Path) {
        path.parent?.let {
            if (!fileSystem.exists(it)) {
                createDirectory(it)
            }
        }

        fileSystem.write(path) { }
    }

    fun createDirectory(path: Path) {
        if (!fileSystem.exists(path)) {
            fileSystem.createDirectories(path)
        }
    }

    fun getKeywordContent(path: Path, keyword: String): String? {
        val text = readFile(path)
        val match = keywordRegex.findAll(text)

        return match
            .find { it.groups[1]?.value == keyword }
            ?.groups[2]?.value ?: ""
    }
}