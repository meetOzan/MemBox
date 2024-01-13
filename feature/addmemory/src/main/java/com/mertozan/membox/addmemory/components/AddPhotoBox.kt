package com.mertozan.membox.addmemory.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.components.CoilImage
import com.mertozan.membox.presentation.theme.ui.DarkWhite60
import com.mertozan.membox.localization.R as localR

@Composable
fun AddPhotoBox(
    data: Uri,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .clip(ShapeDefaults.Medium)
            .background(
                color = DarkWhite60,
            )
    ) {
        if (data.toString().isNotEmpty()) {
            CoilImage(
                data = data.toString(),
                modifier = modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .size(74.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        } else {
            IconButton(
                onClick = onClick,
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.AddAPhoto,
                    contentDescription = stringResource(localR.string.placeholder),
                )
            }
        }
    }
}