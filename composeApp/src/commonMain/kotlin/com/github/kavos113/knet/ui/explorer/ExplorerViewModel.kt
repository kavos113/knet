package com.github.kavos113.knet.ui.explorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kavos113.knet.core.FileManager
import com.github.kavos113.knet.domain.FileItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import okio.Path
import okio.Path.Companion.toPath

class ExplorerViewModel(
    private val fileManager: FileManager
): ViewModel() {
    private val _rawFiles = MutableStateFlow<List<FileItem>>(emptyList())
    private val _openedPaths = MutableStateFlow<Set<Path>>(emptySet())

    val uiFiles: StateFlow<List<FileUiItem>> = combine(_rawFiles, _openedPaths) { files, opened ->
        val t = flattenTree(files, opened)
        println(t)
        t
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    private val rootPath = "".toPath()

    init {
        _rawFiles.value = fileManager.getFileTree(rootPath)
    }

    fun clickDirectory(path: Path) {
        val isOpenUpdated = _openedPaths.value.contains(path)
        _openedPaths.value = if (isOpenUpdated) {
            _openedPaths.value - path
        } else {
            _openedPaths.value + path
        }
    }

    fun update() {
        _rawFiles.value = fileManager.getFileTree(rootPath)
    }

    private fun flattenTree(
        files: List<FileItem>,
        openedPaths: Set<Path>,
        level: Int = 0
    ): List<FileUiItem> {
        val items = mutableListOf<FileUiItem>()

        files.forEach {
            items.add(it.toUiItem(level))
            when(it) {
                is FileItem.DirectoryEntry -> {
                    val isOpened = openedPaths.contains(it.path)
                    if (isOpened) {
                        items.addAll(flattenTree(it.children, openedPaths, level + 1))
                    }
                }
                is FileItem.FileEntry -> { }
            }
        }

        return items
    }
}