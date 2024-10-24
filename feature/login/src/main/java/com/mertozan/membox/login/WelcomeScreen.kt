package com.mertozan.membox.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.R
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.components.LoginAuthButton
import com.mertozan.membox.presentation.theme.ui.Black
import com.mertozan.membox.presentation.theme.ui.IndicatorGray
import com.mertozan.membox.presentation.theme.ui.MainBlue
import com.mertozan.membox.presentation.theme.ui.robotoFamily

import com.mertozan.membox.localization.R.string as localizationR

@Composable
fun WelcomeScreen(
    onSignUpNavigate: () -> Unit,
    onSignInNavigate: () -> Unit
) {

    Column(
        modifier = Modifier.background(Color.White).fillMaxSize().padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(top = 48.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.membox_group),
                contentDescription = stringResource(com.mertozan.membox.localization.R.string.membox_icon),
                modifier = Modifier
                    .padding(bottom = 12.dp)
            )
            CustomText(
                text = stringResource(id = localizationR.welcome_title),
                fontSize = 24,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.SemiBold
            )
            CustomText(
                text = stringResource(id = localizationR.please_select_log_in_way),
                fontSize = 14,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal
            )
        }
        Column {
            ElevatedButton(
                onClick = {
                    onSignInNavigate()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 13.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainBlue
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                CustomText(
                    text = stringResource(localizationR.sign_in),
                    fontSize = 16,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            ElevatedButton(
                onClick = {
                    onSignUpNavigate()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                CustomText(
                    text = stringResource(localizationR.create_account),
                    fontSize = 16,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.weight(1f),
                    color = IndicatorGray
                )
                CustomText(
                    text = stringResource(localizationR.or),
                    fontSize = 14,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = IndicatorGray
                )
                Divider(
                    modifier = Modifier.weight(1f),
                    color = IndicatorGray
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 36.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                LoginAuthButton(
                    modifier = Modifier.weight(1f),
                    buttonText = localizationR.facebook,
                    buttonSrc = R.drawable.facebook_icon,
                    clickAction = {
                        // TODO Click event will be added
                    }
                )
                LoginAuthButton(
                    modifier = Modifier.weight(1f),
                    buttonText = localizationR.google,
                    buttonSrc = R.drawable.google_logo,
                    clickAction = {
                        // TODO Click event will be added
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOfWelcome() {
    WelcomeScreen({}, {})
}