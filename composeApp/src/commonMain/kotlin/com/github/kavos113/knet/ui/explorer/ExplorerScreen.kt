package com.github.kavos113.knet.ui.explorer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.kavos113.knet.ui.CommonColor
import com.github.kavos113.knet.ui.CommonStyle
import knet.composeapp.generated.resources.Res
import knet.composeapp.generated.resources.chevron_right_24px
import knet.composeapp.generated.resources.folder_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DirectoryItem(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(Res.drawable.chevron_right_24px),
            contentDescription = "開く",
            tint = CommonColor.foreground
        )
        Icon(
            painter = painterResource(Res.drawable.folder_24px),
            contentDescription = null,
            tint = CommonColor.folder
        )
        Text(
            text = "closed directory",
            style = CommonStyle.normalTextStyle
        )
    }
}

@Preview
@Composable
private fun DirectoryItemPreview() {
    DirectoryItem(modifier = Modifier.height(12.dp))
}