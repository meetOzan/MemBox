package com.mertozan.membox.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.mertozan.membox.localization.R as localR

@Composable
fun CoilImage(
    data: String,
    modifier: Modifier = Modifier,
) {

    val painter = rememberAsyncImagePainter(data)

    Image(
        painter = painter,
        contentDescription = stringResource(localR.string.coil_image),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
