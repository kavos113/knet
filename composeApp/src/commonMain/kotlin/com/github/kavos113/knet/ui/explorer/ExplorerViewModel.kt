package com.github.kavos113.knet.ui.explorer

import androidx.lifecycle.ViewModel
import com.github.kavos113.knet.core.FileManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okio.Path
import okio.Path.Companion.toPath

class ExplorerViewModel(
    private val fileManager: FileManager
): ViewModel() {
    private val _uiState = MutableStateFlow(ExplorerUiState())
    val uiState: StateFlow<ExplorerUiState> = _uiState.asStateFlow()

    private val rootPath = "".toPath()

    init {
        _uiState.value = _uiState.value.copy(
            files = fileManager.getFileTree(rootPath),
        )
    }

    fun clickDirectory(path: Path, isOpenUpdated: Boolean) {
        _uiState.value = _uiState.value.copy(
            openedPaths = if (isOpenUpdated) {
                _uiState.value.openedPaths - path
            } else {
                _uiState.value.openedPaths + path
            }
        )
    }

    fun update() {
        _uiState.value = _uiState.value.copy(
            files = fileManager.getFileTree(rootPath),
        )
    }
}