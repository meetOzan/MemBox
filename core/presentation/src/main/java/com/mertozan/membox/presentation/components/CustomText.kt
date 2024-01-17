package com.mertozan.membox.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mertozan.membox.presentation.theme.poppinsFamily
import com.mertozan.membox.presentation.theme.ui.LightBlack

@Composable
fun CustomText(
    text: String,
    fontSize: Int = 16,
    color: Color = LightBlack,
    fontFamily: FontFamily = poppinsFamily,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    fontStyle: FontStyle = FontStyle.Normal,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        color = color,
        fontFamily = fontFamily,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        textAlign = textAlign,
        modifier = modifier
    )
}