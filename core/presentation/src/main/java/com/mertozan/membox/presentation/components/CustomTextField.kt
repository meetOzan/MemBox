package com.mertozan.membox.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import com.mertozan.membox.presentation.theme.ui.poppinsFamily
import com.mertozan.membox.presentation.theme.ui.DarkWhite60
import com.mertozan.membox.presentation.theme.ui.DarkWhite70

@Composable
fun CustomTextField(
    textTitle: String,
    onValueChange: (String) -> Unit,
    fontFamily: FontFamily = poppinsFamily,
    maxLines: Int = 1,
    placeHolderText: String = "",
    placeHolderTextColor: Color = Color.Gray,
    textModifier: Modifier = Modifier,
    focusedContainerColor: Color = DarkWhite70,
    unfocusedContainerColor: Color = DarkWhite60,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    suffix: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = textTitle,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontFamily = fontFamily
        ),
        maxLines = maxLines,
        modifier = modifier,
        placeholder = {
            CustomText(
                text = placeHolderText,
                modifier = textModifier,
                color = placeHolderTextColor,
                fontSize = 14
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
        ),
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix
    )
}