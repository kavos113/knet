package com.github.kavos113.knet.ui.explorer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.github.kavos113.knet.domain.FileItem
import com.github.kavos113.knet.ui.CommonColor
import com.github.kavos113.knet.ui.CommonStyle
import knet.composeapp.generated.resources.Res
import knet.composeapp.generated.resources.chevron_right_24px
import knet.composeapp.generated.resources.folder_24px
import knet.composeapp.generated.resources.folder_open_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExplorerScreen(
    modifier: Modifier = Modifier,
    explorerViewModel: ExplorerViewModel = koinViewModel(),
) {
    val uiState by explorerViewModel.uiState.collectAsState()

    LazyColumn {
        items(uiState.files) { file ->
            when(file) {
                is FileItem.DirectoryEntry -> {
                    val isOpen = uiState.openedPaths.contains(file.path)

                    DirectoryItem(
                        name = file.name,
                        isOpen = isOpen,
                        onClickOpen = { explorerViewModel.clickDirectory(file.path, isOpen) }
                    )
                }
                is FileItem.FileEntry -> {
                    OneFileItem(
                        name = file.name
                    )
                }
            }
        }
    }
}

@Composable
private fun DirectoryItem(
    name: String,
    isOpen: Boolean,
    modifier: Modifier = Modifier,
    onClickOpen: () -> Unit = { },
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isOpen) 90f else 0f,
        label = "rotation"
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClickOpen() }
            .height(12.dp)
    ) {
        Icon(
            painter = painterResource(Res.drawable.chevron_right_24px),
            contentDescription = "開く",
            tint = CommonColor.foreground,
            modifier = Modifier
                .graphicsLayer { rotationZ = rotationAngle }
        )
        Icon(
            painter = painterResource(
                if (isOpen) Res.drawable.folder_open_24px else Res.drawable.folder_24px
            ),
            contentDescription = null,
            tint = CommonColor.folder
        )
        Text(
            text = name,
            style = CommonStyle.normalTextStyle
        )
    }
}

@Composable
private fun OneFileItem(
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(12.dp)
            .padding(start = 28.dp)
    ) {
        Text(
            text = name,
            style = CommonStyle.normalTextStyle
        )
    }
}

@Preview
@Composable
private fun DirectoryItemPreview() {
    Column {
        DirectoryItem(
            name = "directory",
            isOpen = false
        )
        DirectoryItem(
            name = "directory",
            isOpen = true
        )
    }
}

@Preview
@Composable
private fun OneFilePreview() {
    OneFileItem("sample.txt")
}