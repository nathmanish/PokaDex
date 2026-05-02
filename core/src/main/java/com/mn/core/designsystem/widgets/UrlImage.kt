package com.mn.core.designsystem.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun UrlImage(
    modifier: Modifier = Modifier,
    url: String,
) {
    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = null
    )
}