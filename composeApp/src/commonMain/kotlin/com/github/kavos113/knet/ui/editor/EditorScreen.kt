package com.github.kavos113.knet.ui.editor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.github.kavos113.knet.ui.CommonColor
import com.github.kavos113.knet.ui.CommonStyle
import knet.composeapp.generated.resources.Res
import knet.composeapp.generated.resources.brightness_1_24px
import knet.composeapp.generated.resources.close_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
private fun EditorTab(
    name: String,
    isSaved: Boolean,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!isSaved) {
            Icon(
                painter = painterResource(Res.drawable.brightness_1_24px),
                contentDescription = null,
                tint = CommonColor.isNotSaved,
                modifier = Modifier
                    .height(5.dp)
                    .width(5.dp)
                    .padding(start = 1.dp)
            )
            Text(
                text = name,
                style = CommonStyle.normalTextStyle
            )
        } else {
            Text(
                text = name,
                style = CommonStyle.normalTextStyle,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
        Icon(
            painter = painterResource(Res.drawable.close_24px),
            contentDescription = null,
            tint = CommonColor.foreground,
            modifier = Modifier
                .height(12.dp)
                .width(12.dp)
                .padding(end = 1.dp)
        )
    }
}

@Preview
@Composable
private fun EditorTabPreview() {
    Column {
        EditorTab(
            name = "sample.md",
            isSaved = true,
            isActive = true,
        )
        EditorTab(
            name = "sample.md",
            isSaved = false,
            isActive = false
        )
    }
}