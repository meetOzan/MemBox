package com.mertozan.membox.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.R
import com.mertozan.membox.presentation.theme.ui.DarkBlue
import com.mertozan.membox.presentation.theme.ui.DarkPink
import com.mertozan.membox.presentation.theme.ui.DarkWhite60
import com.mertozan.membox.presentation.theme.ui.TransparentBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    title: String,
    body: String,
    positiveButtonName: String,
    negativeButtonName: String,
    drawable: Int,
    onDismissClick: () -> Unit = {},
    onPositiveAction: () -> Unit = {},
    onNegativeAction: () -> Unit = {},
) {

    AlertDialog(
        onDismissRequest = {
            onDismissClick()
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            color = DarkWhite60
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = drawable),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .clip(CircleShape)
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.2f)
                        .drawWithContent {
                            drawCircle(
                                color = TransparentBlue.copy(alpha = 0.2f),
                                radius = size.minDimension / 2f
                            )
                            drawContent()
                        }
                )
                CustomText(
                    text = title,
                    color = DarkBlue,
                    fontSize = 24,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 24.dp),
                )
                Spacer(modifier = Modifier.height(4.dp))
                CustomText(
                    text = body,
                    color = Color.Black,
                    fontSize = 20,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextButton(onClick = { onPositiveAction() }) {
                        CustomText(
                            text = positiveButtonName,
                            color = DarkPink,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    TextButton(onClick = { onNegativeAction() }) {
                        CustomText(
                            text = negativeButtonName,
                            color = DarkPink,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AlertDialogPrev() {
    CustomAlertDialog(
        title = "Title",
        body = "Body",
        positiveButtonName = "Positive",
        negativeButtonName = "Negative",
        drawable = R.drawable.angry_cry
    )
}

