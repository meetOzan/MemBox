package com.mertozan.membox

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.goldmanFamily

import com.mertozan.membox.localization.R.string as localString
import com.mertozan.membox.presentation.R.drawable as localDrawable

@Composable
fun SplashScreen(
    onLoginNavigate: () -> Unit,
    onHomeNavigate: () -> Unit,
    splashUiState: SplashUiState,
) {

    LaunchedEffect(!splashUiState.isLoading) {
        if (splashUiState.isSigned) onHomeNavigate()
        else onLoginNavigate()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = localDrawable.membox_icon),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            )
            CustomText(
                text = stringResource(id = localString.membox),
                fontSize = 48,
                fontFamily = goldmanFamily
            )
        }
    }
}

@Preview
@Composable
fun PrevSplash() {
    SplashScreen(
        onLoginNavigate = {},
        onHomeNavigate = {},
        splashUiState = SplashUiState(isLoading = false, isSigned = false)
    )
}