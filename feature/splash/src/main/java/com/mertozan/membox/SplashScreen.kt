package com.mertozan.membox

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudCircle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.localization.R.string as localString

@Composable
fun SplashScreen(
    onLoginNavigate: () -> Unit,
    onHomeNavigate: () -> Unit,
    splashUiState: SplashUiState,
) {

    val scale = remember {
        Animatable(0f)
    }

    val splashAnimateComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url(stringResource(id = localString.lottie_splash))
    )

    LaunchedEffect(splashUiState.isLoading) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        if (!splashUiState.isLoading) {
            if (splashUiState.isSigned) onHomeNavigate()
            else onLoginNavigate()
        }
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
            Box(modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.5f)) {
                LottieAnimation(
                    composition = splashAnimateComposition,
                    iterations = LottieConstants.IterateForever,
                    speed = 2f,
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = 0.8f
                        }
                        .clip(
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomEnd = 0.dp,
                                bottomStart = 0.dp
                            )
                        )
                        .align(
                            Alignment.Center
                        )
                        .fillMaxSize(1f)
                )
                Image(
                    imageVector = Icons.Filled.CloudCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .align(
                            Alignment.Center
                        )
                        .fillMaxSize(0.65f)
                )
            }
            CustomText(
                text = stringResource(id = localString.app_name),
                fontSize = 36,
                fontWeight = FontWeight.Bold
            )
            CustomText(
                text = stringResource(localString.remember_what_you_live),
                fontSize = 20,
                fontStyle = FontStyle.Italic
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