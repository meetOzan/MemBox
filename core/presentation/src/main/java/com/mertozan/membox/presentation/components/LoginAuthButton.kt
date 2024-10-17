package com.mertozan.membox.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.theme.ui.LightGray
import com.mertozan.membox.presentation.theme.ui.TextLightGray

@Composable
fun LoginAuthButton(
    buttonText: Int,
    buttonSrc: Int,
    modifier: Modifier = Modifier,
    clickAction: () -> Unit
) {

    ElevatedButton(
        onClick = {
            clickAction.invoke()
        },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LightGray
        )
    ) {
        Image(
            painter = painterResource(id = buttonSrc),
            contentDescription = stringResource(buttonText),
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(
            modifier = Modifier
                .width(16.dp)
                .padding(vertical = 8.dp)
        )
        CustomText(
            text = stringResource(buttonText),
            fontSize = 16,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.CenterVertically),
            color = TextLightGray
        )
    }
}