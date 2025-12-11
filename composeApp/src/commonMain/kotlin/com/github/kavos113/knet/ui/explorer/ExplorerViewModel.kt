package com.github.kavos113.knet.ui.explorer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okio.Path

class ExplorerViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ExplorerUiState())
    val uiState: StateFlow<ExplorerUiState> = _uiState.asStateFlow()

    fun clickDirectory(path: Path, isOpenUpdated: Boolean) {
        _uiState.value = _uiState.value.copy(
            openedPaths = if (isOpenUpdated) {
                _uiState.value.openedPaths - path
            } else {
                _uiState.value.openedPaths + path
            }
        )
    }
}