@file:OptIn(ExperimentalFoundationApi::class)

package com.mertozan.membox.login

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.components.CustomTextField
import com.mertozan.membox.presentation.components.LoginAuthButton
import com.mertozan.membox.presentation.theme.ui.Black
import com.mertozan.membox.presentation.theme.ui.MainBlue
import com.mertozan.membox.presentation.theme.ui.MainPink
import com.mertozan.membox.presentation.theme.ui.SecondaryPink
import com.mertozan.membox.presentation.theme.ui.TextGray
import com.mertozan.membox.presentation.theme.ui.robotoFamily
import kotlinx.coroutines.launch

import com.mertozan.membox.presentation.R.drawable as presentationR
import com.mertozan.membox.localization.R.string as localizationR

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    loginAction: (LoginAction) -> Unit,
    uiState: LoginUiState,
    onHomeScreenNavigate: () -> Unit,
) {

    val signInFieldList = listOf(
        LoginFieldClass(
            text = stringResource(id = localizationR.email),
            value = uiState.email
        ),
        LoginFieldClass(
            text = stringResource(id = localizationR.password),
            value = uiState.password
        ),
        LoginFieldClass(
            text = stringResource(id = localizationR.username),
            value = uiState.username
        )
    )

    val pagerState = rememberPagerState(pageCount = { 2 })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
        ) {
            Image(
                painter = painterResource(id = presentationR.membox_group),
                contentDescription = stringResource(localizationR.membox_icon),
                modifier = Modifier
                    .padding(end = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(vertical = 16.dp),
            pageSpacing = 16.dp,
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> {
                    SignInScreen(
                        loginFieldList = signInFieldList,
                        loginAction = loginAction,
                        uiState = uiState,
                        onHomeScreenNavigate = onHomeScreenNavigate,
                        pagerState = pagerState
                    )
                }

                1 -> {
                    SignUpScreen(
                        loginFieldList = signInFieldList,
                        loginAction = loginAction,
                        uiState = uiState,
                        onHomeScreenNavigate = onHomeScreenNavigate,
                        pagerState = pagerState
                    )
                }
            }
        }
    }
}

@Composable
fun SignInScreen(
    loginFieldList: List<LoginFieldClass>,
    loginAction: (LoginAction) -> Unit,
    uiState: LoginUiState,
    onHomeScreenNavigate: () -> Unit,
    pagerState: PagerState
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn {
            item {
                CustomText(
                    text = stringResource(localizationR.sign_in),
                    fontFamily = robotoFamily,
                    fontSize = 25,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
            }
            items(loginFieldList.size - 1) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CustomText(
                        text = loginFieldList[index].text,
                        fontSize = 14,
                        color = TextGray,
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                    if (index == 0) {
                        CustomTextField(
                            textTitle = loginFieldList[index].value,
                            onValueChange = {
                                loginAction(
                                    LoginAction.EmailChanged(
                                        it
                                    )
                                )
                            },
                            placeHolderText = loginFieldList[index].text,
                            placeHolderTextColor = TextGray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp)
                        )
                    } else {
                        CustomTextField(
                            textTitle = loginFieldList[index].value,
                            onValueChange = {
                                loginAction(
                                    LoginAction.PasswordChanged(
                                        it
                                    )
                                )
                            },
                            placeHolderText = loginFieldList[index].text,
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
                                                presentationR.opened_eye
                                            )
                                        else painterResource(
                                            presentationR.closed_eye
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
                    }
                }
            }
            item {
                CustomText(
                    text = stringResource(localizationR.forgot_password),
                    fontSize = 14,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    textAlign = TextAlign.End,
                    color = MainBlue
                )
            }
            item {
                ElevatedButton(
                    onClick = {
                        loginAction(
                            LoginAction.SignIn(
                                onNavigate = {
                                    onHomeScreenNavigate()
                                },
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainBlue
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    CustomText(
                        text = stringResource(localizationR.login),
                        fontSize = 16,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier.weight(1f),
                        color = Color.Black
                    )
                    CustomText(
                        text = stringResource(localizationR.or),
                        fontSize = 14,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Divider(
                        modifier = Modifier.weight(1f),
                        color = Color.Black
                    )
                }
            }
            item {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    LoginAuthButton(
                        modifier = Modifier.weight(1f),
                        buttonText = localizationR.facebook,
                        buttonSrc = presentationR.facebook_icon,
                        clickAction = {
                            // TODO Click event will be added
                        }
                    )
                    LoginAuthButton(
                        modifier = Modifier.weight(1f),
                        buttonText = localizationR.google,
                        buttonSrc = presentationR.google_logo,
                        clickAction = {
                            // TODO Click event will be added
                        }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomText(
                text = stringResource(id = localizationR.you_don_t_have_an_account),
                fontSize = 14,
                modifier = Modifier
                    .padding(top = 16.dp, end = 2.dp),
                color = Black
            )
            CustomText(
                text = stringResource(id = localizationR.sign_up),
                fontSize = 14,
                modifier = Modifier
                    .padding(top = 16.dp, start = 2.dp)
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    },
                textAlign = TextAlign.Center,
                color = MainBlue
            )
        }
    }
}

@Composable
fun SignUpScreen(
    loginFieldList: List<LoginFieldClass>,
    loginAction: (LoginAction) -> Unit,
    uiState: LoginUiState,
    onHomeScreenNavigate: () -> Unit,
    pagerState: PagerState
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn {
            item {
                CustomText(
                    text = stringResource(localizationR.sign_up),
                    fontFamily = robotoFamily,
                    fontSize = 25,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
            }
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    LoginAuthButton(
                        modifier = Modifier.weight(1f),
                        buttonText = localizationR.facebook,
                        buttonSrc = presentationR.facebook_icon,
                        clickAction = {
                            // TODO Click event will be added
                        }
                    )
                    LoginAuthButton(
                        modifier = Modifier.weight(1f),
                        buttonText = localizationR.google,
                        buttonSrc = presentationR.google_logo,
                        clickAction = {
                            // TODO Click event will be added
                        }
                    )
                }
            }
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier.weight(1f),
                        color = Color.Black
                    )
                    CustomText(
                        text = stringResource(localizationR.or),
                        fontSize = 14,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Divider(
                        modifier = Modifier.weight(1f),
                        color = Color.Black
                    )
                }
            }
            items(loginFieldList.size) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CustomText(
                        text = loginFieldList[index].text,
                        fontSize = 14,
                        color = TextGray,
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                    if (index == 1) {
                        CustomTextField(
                            textTitle = loginFieldList[index].value,
                            onValueChange = {
                                loginAction(
                                    LoginAction.PasswordChanged(
                                        it
                                    )
                                )
                            },
                            placeHolderText = loginFieldList[index].text,
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
                                                presentationR.opened_eye
                                            )
                                        else painterResource(
                                            presentationR.closed_eye
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
                            textTitle = loginFieldList[index].value,
                            onValueChange = {
                                loginAction(
                                    LoginAction.EmailChanged(
                                        it
                                    )
                                )
                            },
                            placeHolderText = loginFieldList[index].text,
                            placeHolderTextColor = TextGray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp)
                        )
                    }
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ElevatedButton(
                onClick = {
                    loginAction(
                        LoginAction.SignUp(
                            onNavigate = {
                                onHomeScreenNavigate()
                            },
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainPink
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                CustomText(
                    text = stringResource(localizationR.register),
                    fontSize = 16,
                    color = Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CustomText(
                    text = stringResource(id = localizationR.you_have_an_account),
                    fontSize = 14,
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    color = Black
                )
                CustomText(
                    text = stringResource(id = localizationR.sign_in),
                    fontSize = 14,
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        },
                    textAlign = TextAlign.Center,
                    color = SecondaryPink
                )
            }
        }
    }
}

data class LoginFieldClass(
    val text: String = "",
    val value: String = "",
)

@Preview
@Composable
private fun PreviewOfLogin() {
    LoginScreen(
        loginAction = {},
        uiState = LoginUiState(),
        onHomeScreenNavigate = {}
    )
}