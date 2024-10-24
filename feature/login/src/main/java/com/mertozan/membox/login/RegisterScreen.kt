package com.mertozan.membox.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.localization.R
import com.mertozan.membox.login.viewmodel.LoginAction
import com.mertozan.membox.login.viewmodel.LoginUiState
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.components.CustomTextField
import com.mertozan.membox.presentation.theme.ui.Black
import com.mertozan.membox.presentation.theme.ui.TextGray
import com.mertozan.membox.presentation.theme.ui.robotoFamily

@Composable
fun RegisterScreen(
    uiState: LoginUiState,
    loginAction: (LoginAction) -> Unit,
    onOnboardingNavigate: () -> Unit
) {

    val signUpFieldList = listOf(
        LoginFieldClass(
            text = stringResource(id = R.string.email),
            value = uiState.signUpEmail
        ),
        LoginFieldClass(
            text = stringResource(id = R.string.password),
            value = uiState.signUpPassword
        ),
        LoginFieldClass(
            text = stringResource(id = R.string.username),
            value = uiState.username
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp, bottom = 56.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
        ) {
            Image(
                painter = painterResource(id = com.mertozan.membox.presentation.R.drawable.membox_group),
                contentDescription = stringResource(R.string.membox_icon),
                modifier = Modifier
                    .padding(end = 8.dp)
            )
        }

        LazyColumn {
            item {
                CustomText(
                    text = stringResource(R.string.sign_up),
                    fontFamily = robotoFamily,
                    fontSize = 28,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
            }
            items(signUpFieldList.size) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CustomText(
                        text = signUpFieldList[index].text,
                        fontSize = 14,
                        color = TextGray,
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                    if (index == 1) {
                        CustomTextField(
                            textTitle = signUpFieldList[index].value,
                            onValueChange = {
                                loginAction(
                                    LoginAction.RegisterPasswordChanged(
                                        it
                                    )
                                )
                            },
                            placeHolderText = signUpFieldList[index].text,
                            placeHolderTextColor = TextGray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp),
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        loginAction(LoginAction.IsPasswordVisible)
                                    },
                                ) {
                                    Icon(
                                        painter = if (uiState.isPasswordVisible)
                                            painterResource(
                                                com.mertozan.membox.presentation.R.drawable.opened_eye
                                            )
                                        else painterResource(
                                            com.mertozan.membox.presentation.R.drawable.closed_eye
                                        ),
                                        modifier = Modifier.size(24.dp),
                                        contentDescription = null,
                                        tint = Color.Black
                                    )
                                }
                            },
                            visualTransformation =
                            if (uiState.isPasswordVisible) VisualTransformation.None
                            else PasswordVisualTransformation(),
                        )
                    } else {
                        CustomTextField(
                            textTitle = signUpFieldList[index].value,
                            onValueChange = {
                                if (index == 0)
                                    loginAction(
                                        LoginAction.RegisterEmailChanged(
                                            it
                                        )
                                    )
                                else loginAction(
                                    LoginAction.UsernameChanged(
                                        it
                                    )
                                )
                            },
                            placeHolderText = signUpFieldList[index].text,
                            placeHolderTextColor = TextGray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp)
                        )
                    }
                }
            }
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    ElevatedButton(
                        onClick = {
                            loginAction(
                                LoginAction.SignUp(
                                    onNavigate = {
                                        onOnboardingNavigate()
                                    },
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        CustomText(
                            text = stringResource(R.string.register),
                            fontSize = 16,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOfRegisterScreen() {
    RegisterScreen(
        loginAction = {},
        uiState = LoginUiState()
    ) {}
}